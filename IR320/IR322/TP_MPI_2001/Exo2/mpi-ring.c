/*
 * Laboratory for Scientific Computing
 * http://www.lam-mpi.org/tutorials/
 * University of Notre Dame
 *
 * MPI Tutorial
 * The cannonical ring program
 */

#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include "mpi.h"

int main(int argc, char *argv[])
{
  MPI_Status status;
  int num, rank, size, tag, next, from;

  if (argc != 2) {
    printf("appel : nom du programme nbre de tours \n");
    exit(-1);
  }

  /* Start up MPI */

  MPI_Init(&argc, &argv);
  MPI_Comm_rank(MPI_COMM_WORLD, &rank);
  MPI_Comm_size(MPI_COMM_WORLD, &size);

  /* Arbitrarily choose 201 to be our tag.  Calculate the */
  /* rank of the next process in the ring.  Use the modulus */
  /* operator so that the last process "wraps around" to rank */
  /* zero. */

  tag = 201;
  next = (rank + 1) % size;
  from = (rank + size - 1) % size;

  /* If we are the "console" process, get a integer from the */
  /* user to specify how many times we want to go around the */
  /* ring */

  if (rank == 0) {
    num = atoi (argv[1]);

    printf("Process %d sending %d to %d\n", rank, num, next);
    MPI_Send(&num, 1, MPI_INT, next, tag, MPI_COMM_WORLD); 
  }

  /* Pass the message around the ring.  The exit mechanism works */
  /* as follows: the message (a positive integer) is passed */
  /* around the ring.  */

  while (1) {
    MPI_Recv(&num, 1, MPI_INT, from, tag, MPI_COMM_WORLD, &status);

    printf("Process %d received %d\n", rank, num);

    if (rank == 0) {
      num--;
      printf("Process 0 decremented num\n");
    }

    if (num == 0) {
      printf("Process %d exiting\n", rank);
      MPI_Send(&num, 1, MPI_INT, next, tag, MPI_COMM_WORLD);
      break;
    }

    printf("Process %d sending %d to %d\n", rank, num, next);
    MPI_Send(&num, 1, MPI_INT, next, tag, MPI_COMM_WORLD);
  }

  /* The last process does one extra send to process 0, which needs */
  /* to be received before the program can exit */

	if (rank == 0)
		MPI_Recv(&num, 1, MPI_INT, from, tag, MPI_COMM_WORLD, &status);

  /* Quit */

  MPI_Finalize();
  return 0;
}
