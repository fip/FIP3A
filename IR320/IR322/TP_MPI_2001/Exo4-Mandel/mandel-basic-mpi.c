/*
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/*
	Import de la librairie standard et de la librairie MPI
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mpi.h>


#if defined(__GNUC__) && (__GNUC__ >= 3)
	#define ATTRIBUTE(x) __attribute__(x)
#else
	#define ATTRIBUTE(x) 
#endif

/*
	Macro mathématiques
*/
#define MIN(_x, _y) ((_x) > (_y) ? (_y) : (_x))
#define ABS(_x) ((_x) >= 0 ? (_x) : -(_x))


/* 
	taille de l'image.
*/
#define MAXX  2400
#define MAXY (MAXX * 3 / 4)

#define NX (2 * MAXX + 1)
#define NY (2 * MAXY + 1)

/*
	Définition des tags pour la communication
*/
#define NBITER 550
#define DATATAG 150
#define COMTAG 150
//#define DEBUG 1

static int mandel(double, double);
int dump_ppm(const char *, int[NX][NY]);
int cases[NX][NY];


int main(int argc, char *argv[])
{


	/*
		Initialisation des variables
	*/
	MPI_Status status;
	int i,j, num, rank, size, nbslaves;
	char inputstr [100],outstr [100];

	/* 
		Démarrage de l'envirronement MPI 
	*/

	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	MPI_Comm_size(MPI_COMM_WORLD, &size);

	// Nombre de noeuds
	nbslaves = size -1;

	#ifdef DEBUG
		fprintf(stderr, "# Nombre d'esclaves : %d\n",nbslaves);
	#endif
	
	if (rank == 0) {
		/*
			Processus maître
		*/
		
		/*
			bloc_size		: Nombre de blocs4
			resp			: buffer pour la réponse : la taille est volontairement
							  grande pour contenir le padding
			resp[x][y]		: x : ligne, y : colonne
			
			res				: Taille des données d'une colonne 
			res[0]			: colonne à traiter
			res[1 à NY+1]	: données calculées
		*/
		int bloc_size;
		
		int res[NY+1];
		
		int j;
		/*
			Vérification des arguments pour le choix de la taille des blocs
		*/
		if (argc != 2){
			fprintf(stderr,"Usage : mpirun --np 3  --hostfile ../hosts mandel-basic-mpi sizeb\n");
			fprintf(stderr," 		sizeb : taille du coté d'un bloc \n");
			for( i=1 ; i < nbslaves+1 ; i++ ) {
				MPI_Send(&i, 1 , MPI_INT, i, 51, MPI_COMM_WORLD);
			}	
			MPI_Finalize();
			return 0;	
		} else {
			bloc_size = atoi(argv[1]);
			/*
				Vérification de la taille de bloc saisie
			*/
			if (bloc_size > NX || bloc_size > NY) {
				fprintf(stderr,"Usage : mpirun --np 3  --hostfile ../hosts mandel-basic-mpi sizeb\n");
				fprintf(stderr," 		ERREUR : taille de bloc trop grande\n");
				for( i=1 ; i < nbslaves+1 ; i++ ) {
					MPI_Send(&i, 1 , MPI_INT, i, 51, MPI_COMM_WORLD);
				}
				MPI_Finalize();
				return 0;		
			}
			printf("# Taille de bloc utilisée : %d \n",bloc_size);
		}

		
		/*
			Calcul des coordonées des blocs
			
		*/
		int resp[bloc_size][bloc_size];
		int nb_bloc,nb_bloc_x,nb_bloc_y;
		nb_bloc_x = NX / bloc_size; 
		nb_bloc_y = NY / bloc_size;
		nb_bloc = nb_bloc_x*nb_bloc_y;	
		printf("nb_bloc_x:%d,nb_bloc_y:%d\n",nb_bloc_x,nb_bloc_y);
		/*
			coor		: Coordonnées des blocs
			coor[n]		: numéro du bloc
			coor[n][0]	: x
			coor[n][1]	: y
			coor[n][2]	: x+i
			coor[n][3]	: y+i
		*/
		int coor[nb_bloc][5];
		
		/*
			Tableau contenant les coordonnées de tous les blocs
		*/
		int nb_bloc_tmp=0;
		for(i = 0; i <=nb_bloc_x-1; i++) {
			for(j = 0; j <=nb_bloc_y-1; j++) {
				 
				// x
				coor[nb_bloc_tmp][0]=-MAXX+i*bloc_size; 
				// y
				coor[nb_bloc_tmp][1]=-MAXY+j*bloc_size;
			
				//x+1
				if(i<nb_bloc_x-1){
					coor[nb_bloc_tmp][2]=-MAXX+i*bloc_size+bloc_size;
				} else {
					coor[nb_bloc_tmp][2]=MAXX;
				}
				
				//y+i
				if(j<nb_bloc_y-1){
					coor[nb_bloc_tmp][3]=-MAXY+j*bloc_size+bloc_size;
				} else {
					coor[nb_bloc_tmp][3]=MAXY;
				}
				// numéro du bloc
				coor[nb_bloc_tmp][4]=nb_bloc_tmp;
				#ifdef DEBUG
					printf("i:%d,j:%d = coord[%d]x:%d,y:%d,x+i:%d,y+i:%d,numblock=%d\n",i,j,nb_bloc_tmp,coor[nb_bloc_tmp][0],coor[nb_bloc_tmp][1],coor[nb_bloc_tmp][2],coor[nb_bloc_tmp][3],coor[nb_bloc_tmp][4]);
				#endif
				// gnuplot printf("%d %d\n%d %d\n",coor[nb_bloc_tmp][0],coor[nb_bloc_tmp][1],coor[nb_bloc_tmp][2],coor[nb_bloc_tmp][3]);
				nb_bloc_tmp++;
			}
		}
		
		
		/*
			Envoi de tâches à réaliser aux esclaves
			Les tâches sont envoyées au client  (i modulo le nombre d'esclave) +1 
		*/
		
		for(i = 0; i <= nb_bloc; i++) {
			
			
			#ifdef DEBUG
				fprintf(stderr, "# Envoi du bloc %d à %d mod %d = %d \n",i,i,nbslaves+1,(i+nb_bloc)%nbslaves+1);
			#endif
			
			// Envoi des coordonnées (5 int)
			MPI_Send(&coor[i], 5 , MPI_INT, (i+nb_bloc)%nbslaves+1, COMTAG, MPI_COMM_WORLD);	
		}

		/*
			Réception des tâches et assemblage des réponses dans la matrice 
			'cases' finale.
		*/
		for(i =0; i <= nb_bloc; i++) {

			// Réception d'une requete
			MPI_Recv(&resp, bloc_size*bloc_size, MPI_INT, MPI_ANY_SOURCE, DATATAG, MPI_COMM_WORLD, &status);
			
			//Récupération du numéro de bloc dans resp[bloc_size*2][bloc_size*2]
			int bloc_num=resp[bloc_size-1][bloc_size-1];
			
			#ifdef DEBUG
				fprintf(stderr, "# Réception du bloc %d de %d sur le maitre\n",bloc_num,status.MPI_SOURCE);
			#endif
	

			/* 
				Assemblage des réponses  
			*/
			
			int k=0,l=0,m;
			// parcours de x à x+i
			#ifdef DEBUG
				printf("bloc_num=%d, coor[bloc_num][0]=%d, coor[bloc_num][2]=%d\n",bloc_num,coor[bloc_num][0],coor[bloc_num][2]);
				printf("bloc_num=%d, coor[bloc_num][1]=%d, coor[bloc_num][3]=%d\n",bloc_num,coor[bloc_num][1],coor[bloc_num][3]);
			#endif
			
			for(m = coor[bloc_num][0]; m <= coor[bloc_num][2]-1; m++) {
				// parcours de j à j+i
				for(j = coor[bloc_num][1]; j <= coor[bloc_num][3]-1; j++) {
					cases[MAXX+m][MAXY+j]=resp[l][k];
					
					//printf("#%d %d : %d %d#",MAXX+i,MAXY+j,k,l);
					//printf("#%d %d : %d#",k,l,resp[k][l]);
					k++;
				}
				//printf("\n\nS");
				k=0;
				l++;
			}


		}
		
		// création de l'image
		dump_ppm("mandel.ppm", cases);

		/*
			Envoi d'un signal aux esclaves pour indiquer la terminaison.
		*/
		for( i=1 ; i < nbslaves+1 ; i++ ) {
			MPI_Send(&i, 1 , MPI_INT, i, 51, MPI_COMM_WORLD);
		}	

		printf("Fini.\n");
		
		MPI_Finalize();
		return 0;
	} else {
		/*
			Processus esclave
		*/
		
		/*
			On éxecute en boucle le programme de traitement des réponses sur
			l'esclave afin de pouvoir recevoir plusieurs tâches.
		*/
		while(1) {
		
		
			/*
			bloc_size		: Nombre de blocs4
			resp			: buffer pour la réponse : la taille est volontairement
							  grande pour contenir le padding
			resp[x][y]		: x : ligne, y : colonne
			
			res				: Taille des données d'une colonne 
			res[0]			: colonne à traiter
			res[1 à NY+1]	: données calculées
			*/
			//int bloc_size;
			//int resp[bloc_size][bloc_size];
			
			/*
			coord		: Coordonnées des blocs
			coord[0]	: x
			coord[1]	: y
			coord[2]	: x+i
			coord[3]	: y+i
			coord[4]	: numéro du bloc
			*/
			int coord[5];
		
		
			/*
				Définition des variables
				i : indice de colonne
				j : indice de ligne
				res[NY+1] valeurs d'une colonne
				res[0] : numéro de la colonne
			*/
			int i, j, res[NY+1];
			
			
			

			/*
				Réception d'une tache de calcul pour la colonne 
			*/
			MPI_Recv(&coord, 5, MPI_INT, 0, MPI_ANY_TAG, MPI_COMM_WORLD, &status);
			
			#ifdef DEBUG
				printf("#ESCLAVE %d : x:%d,y:%d,x+i:%d,y+i:%d,numblock:%d\n",rank,coord[0],coord[1],coord[2],coord[3],coord[4]);
			#endif
			int bloc_size=coord[2]-coord[0];
			int resp[bloc_size][bloc_size];
			/*
				Si le tag du message précédent contient comme information un 
				numéro de colonne 'COMTAG') 
			*/
			if(status.MPI_TAG == COMTAG) {
				#ifdef DEBUG
					fprintf(stderr, "# Réception de la tâche %d sur l'esclave %d\n",coord[4],rank);
				#endif
				
				double x, y;
				// parcours de x à x+i
				for(i =0; i <= bloc_size-1; i++) {
					// parcours de j à j+i
					for(j = 0; j <= bloc_size-1; j++) {
					
						x = 2 * (coord[0]+i) / (double)MAXX;
                		y = 1.5 * (coord[1]+j) / (double)MAXY;	
						resp[i][j]=mandel(x,y);
						//printf("Rank=%d x'=%d;y'=%d\n",rank,coord[0]+i,coord[2]+j);
						//fprintf(stderr, "# %d mandel en i:%d,j:%d = %d\n",rank,i,j,resp[i][j]);
						//fprintf(stderr, "# %d mandel = en i:%d,j:%d = %d\n",rank,i,j,resp[i][j]);
					}
				}
				//printf("#%d # Après mandel\n",rank);
				resp[bloc_size-1][bloc_size-1]=coord[4];
				//printf("#%d # Après coord i:%d,j%d: %d < %d \n",rank,i,j,resp[bloc_size-1][bloc_size-1],coord[4]);
				/*
					Envoi de la réponse
				*/
				int ans = MPI_Send(&resp, bloc_size*bloc_size , MPI_INT, 0, DATATAG, MPI_COMM_WORLD);
				#ifdef DEBUG
					printf("#%d # Après mpi_send : %d\n",rank,ans);
				#endif
			} else  {
				/*
					Si le message reçu n'est pas de type COMTAG, alors c'est que
					le calcul est fini 
				*/
				
				#ifdef DEBUG
					fprintf(stderr, "# Harakiri de l'esclave %d\n",rank);
				#endif
				MPI_Finalize();
				return 0;
			}
		}
	}
	MPI_Finalize();
	return 0;
}



	/* function to compute a point - the number of iterations 
	plays a central role here */

int
mandel(double cx, double cy)
	{
	int i;
	double zx, zy;
	zx = 0.0; zy = 0.0;
	for(i = 0; i < NBITER; i++) {
		double zxx = zx, zyy = zy;
		zx = zxx * zxx - zyy * zyy + cx;
		zy = 2 * zxx * zyy + cy;
		if(zx * zx + zy * zy > 4.0)
			return i;
	}
	return -1;
}

/* the image commputer can be transformed in a ppm file so
to be seen with xv */

int
dump_ppm(const char *filename, int valeurs[NX][NY])
{
	FILE *f;
	int i, j, rc;

	f = fopen(filename, "w");
	if(f == NULL) { perror("fopen"); exit(1); }
		fprintf(f, "P6\n");
		fprintf(f, "%d %d\n", NX, NY);
		fprintf(f, "%d\n", 255);
		for(j = NY - 1; j >= 0; j--) {
			for(i = 0; i < NX; i++) {
				unsigned char pixel[3];
				if(valeurs[i][j] < 0) {
					pixel[0] = pixel[1] = pixel[2] = 0;
				} else {
					unsigned char val = MIN(valeurs[i][j] * 11, 255);
					pixel[0] = val;
					pixel[1] = 0;
					pixel[2] = 255 - val;
				}
			rc = fwrite(pixel, 1, 3, f);
			if(rc < 0) { perror("fwrite"); exit(1); }
		}
	}
	fclose(f);
	return 0;
}
