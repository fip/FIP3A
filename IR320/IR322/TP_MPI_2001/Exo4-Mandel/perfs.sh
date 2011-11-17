#!/bin/bash
for i in `seq 2 11`; 
do 
	rm -f mandel.ppm
	echo "### $i HOSTS ###"
	echo "### $i HOSTS ###"
	time mpirun --np $i  --hostfile ../hosts mandel-basic-mpi 100
	echo "### $i HOSTS ###"
	echo "### $i HOSTS ###"
	time mpirun --np $i  --hostfile ../hosts mandel-basic-mpi 200
	echo "### $i HOSTS ###"
	echo "### $i HOSTS ###"
	time mpirun --np $i  --hostfile ../hosts mandel-basic-mpi 300
	echo "### $i HOSTS ###"
	echo "### $i HOSTS ###"
	time mpirun --np $i  --hostfile ../hosts mandel-basic-mpi 400
	echo "### $i HOSTS ###"
	echo "### $i HOSTS ###"
	time mpirun --np $i  --hostfile ../hosts mandel-basic-mpi 600
done

