#include <stdio.h>
#include <string.h>
#include "mpi.h"

#define MAX_LENGTH 100 /* maximum length of exchanged strings, excluding '\0' */

int main(int argc, char *argv[])
{
  MPI_Status status;
  int i,j, cutsize, num, rank, size, tag,   nbslaves;
  char inputstr[MAX_LENGTH+1], outstr[MAX_LENGTH+1];


  /* Start up MPI */

  MPI_Init(&argc, &argv);
  MPI_Comm_rank(MPI_COMM_WORLD, &rank);
  MPI_Comm_size(MPI_COMM_WORLD, &size);
 
  /* Arbitrarily choose 201 to be our tag.  */

  tag = 201;

  nbslaves = size -1;

  if (rank == 0) {

    if (strlen(argv[1]) > MAX_LENGTH * nbslaves) {
      printf("The string to convert is too long, shrinking\n");
      argv[1][MAX_LENGTH*nbslaves]='\0';
    }

    /* Begin User Program  - the master */
     printf("number of arguments in the command line %d  \n", argc);

     printf("I 'll convert  %s using %d slaves \n", argv [1], nbslaves);

     cutsize = strlen(argv[1]) / nbslaves * sizeof(char);

    /* send data to slave tasks */
    for( i=0 ; i < nbslaves ; i++ ) {
      /* prepare the data to be sent */
      /* We cut argv[1] into nbslaves pieces; the (nbslaves-1) 1st pieces
	 have a length equal to cutsize, the last one has a maximum length of
	 (cutsize + strlen(argv[1]) modulo nbslaves) < cutsize+nbslaves */
      strncpy(outstr, argv[1]+(i*cutsize), cutsize+nbslaves);
      if (i != (nbslaves-1)) // The last part already contains \0, no need to add it
	outstr[cutsize]='\0';

    printf("Process sending %s to %d\n", outstr, i);

    MPI_Send(outstr, strlen(outstr)+1, MPI_CHAR, i+1, tag, MPI_COMM_WORLD); 
  }
    for( i=1 ; i < nbslaves+1 ; i++ ) {
    MPI_Recv(&inputstr, MAX_LENGTH+1, MPI_CHAR, i, tag+1, MPI_COMM_WORLD, &status);
    printf("Process receiving %s from node %d \n", inputstr, i);
    }
    }

  

else {

    MPI_Recv(&inputstr, MAX_LENGTH+1, MPI_CHAR, 0, tag, MPI_COMM_WORLD, &status);
 
    /* Do upper-case translation with data */
    for (i=0; inputstr [i] != '\0' && (i < MAX_LENGTH); i++)
        /*
         * The function toupper() converts ASCII characters
           to upper case.
         */
        outstr[i] = toupper(inputstr[i]);

    outstr[i] = '\0';
    MPI_Send(outstr, strlen(outstr)+1 , MPI_CHAR, 0, tag+1, MPI_COMM_WORLD); 
}

  fflush(stdout);
  MPI_Finalize();
  return 0;
}
 

