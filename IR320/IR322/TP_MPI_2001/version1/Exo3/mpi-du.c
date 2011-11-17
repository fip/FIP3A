#include <stdio.h>
#include <string.h>
#include "mpi.h"
#include <dirent.h>


int main(int argc, char *argv[])
{
  MPI_Status status;
  int i,j, num, rank, size, tag, next, from, nbslaves;
  FILE *fp;
  DIR *dirp;
  int notover;
  int  receivedsize;

  int partialsize, totalsize;
  char instr [100], base[256], cmd[256], buf[256];
  struct dirent *direntp;


  /* Start up MPI */

  MPI_Init(&argc, &argv);
  MPI_Comm_rank(MPI_COMM_WORLD, &rank);
  MPI_Comm_size(MPI_COMM_WORLD, &size);
 
  /* Arbitrarily choose 201 to be our tag.  Calculate the */
  /* rank of the next process in the ring.  Use the modulus */
  /* operator so that the last process "wraps around" to rank */
  /* zero. */

  notover = 1;
  totalsize = 0;
  tag = 201;
  next = (rank + 1) % size;
  from = (rank + size - 1) % size;

  nbslaves = size -1;

  int wait_for_nb_slaves=0;

  if (rank == 0) {

  /* Begin User Program - the master */

        dirp = opendir(argv[1]);
        if (dirp == NULL) {
          perror("Erreur sur ouverture repertoire");
          exit(1);
        }
	sprintf(base, "du -s %s/", argv[1]);
        while (notover) {
	  wait_for_nb_slaves=0;

          for( i=1 ; i < nbslaves+1 ; i++ ) {

            if ( (direntp = readdir( dirp )) != NULL ) {
              if(!strcmp(direntp->d_name, "."))
                 { i= i-1; continue;}
              if(!strcmp(direntp->d_name, ".."))
                 {i = i -1; continue;}
              strcpy(cmd, base);
              strcat(cmd, direntp->d_name);
	      wait_for_nb_slaves++;
            }
            else {
               notover = 0;
	    }

	    if (notover)
	      {
            printf("I've sent  %s to  slave number %d w/ length \n",
		   cmd, i, strlen(cmd));
    printf("Process sending %s to %d\n", cmd, i);
    MPI_Send(cmd, strlen(cmd)+1, MPI_CHAR, i, tag, MPI_COMM_WORLD); 
	      }
          }

        /* Wait for results from slaves */
         for( i=1 ; i<wait_for_nb_slaves+1 ; i++ ){
        printf("attente  maitre %d\n", i);
            MPI_Recv(&partialsize, 1, MPI_INT, i, tag+1, MPI_COMM_WORLD, &status);
        printf("fin attente  maitre %d\n", i);
            totalsize = totalsize + partialsize;
            printf("I got %d from node %d \n",partialsize, i);
            printf("Total %d  \n",totalsize);
         }

	}
  
        printf("calcul termine pour le maitre\n");
        (void)closedir( dirp );
        printf("close dir  pour le maitre\n");

        for( i=1 ; i < nbslaves+1 ; i++ ) {
           MPI_Send(cmd, 0, MPI_CHAR, i, tag, MPI_COMM_WORLD); }

  }
  

else {


     while (notover) {

    size = 0;
    /* Receive data from master */

     MPI_Recv(instr, 100, MPI_CHAR, 0, tag, MPI_COMM_WORLD, &status);

/* we call MPI_Get8count to determine the size of the message received */

    MPI_Get_count (&status, MPI_CHAR, &receivedsize);
    if ( receivedsize== 0) {notover = 0;}
    else {



          fp = popen(instr, "r");
          while (fgets(buf, BUFSIZ, fp) != NULL) {
            size = size + atoi(buf);
          }
          fclose(fp);
	  printf("slave %i: executed %s returning: %i\n", rank, instr, size);
       /* Send result to master */
    MPI_Send(&size, 1, MPI_INT, 0, tag+1, MPI_COMM_WORLD); 
     }
    }

}
        printf("calcul finalise\n");

  MPI_Finalize();
  return 0;
}
 

