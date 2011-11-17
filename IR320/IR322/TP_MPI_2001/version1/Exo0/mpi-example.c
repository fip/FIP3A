#include <stdio.h>
#include "mpi.h"


int main(int argc, char *argv[])
{


     double startwtime, endwtime;
     int namelen, nprocs, myid;
     char name_proc[MPI_MAX_PROCESSOR_NAME];

     /* Initialisation de mpi */
     MPI_Init (&argc, &argv);
     /* Heure de départ */
     startwtime = MPI_Wtime();
     /* Combien y a-t-il de processeurs ? */
     MPI_Comm_size (MPI_COMM_WORLD, &nprocs);
     /* Numéro du processus courant */
     MPI_Comm_rank (MPI_COMM_WORLD, &myid);
     /* Nom du processeur courant */
     MPI_Get_processor_name(name_proc, &namelen);

     printf("Processus %d sur la machine %s parmi %d processus.\n", myid, name_proc, nprocs);

     /* Heure de fin */
     endwtime = MPI_Wtime();
     printf("Temps ecoule sur %d = %f\n", myid, endwtime-startwtime);

     /* Fin */
     MPI_Finalize();

     return 0;
   }

