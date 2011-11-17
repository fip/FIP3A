CC                   = /usr/bin/mpicc
MPE_CC               = /usr/local/mpe/bin/mpecc
MPI_CFLAGS           = 
MPI_LDFLAGS          = 
CFLAGS               = 
LDFLAGS              = -lX11 -Wl,-hash-style=sysv

RM = rm

srcdir = .
VPATH=.:$(srcdir)

#
# Rules

.SUFFIXES: .c .o

.c.o: 
	$(CC) $(CFLAGS) $(MPI_CFLAGS) -c $<

# Targets
#.PHONY clean

default: all
all: mandel-basic-mpi

mandel-basic-mpi: mandel-basic-mpi.o
	$(MPE_CC) -mpilog -o $@ $? $(LD_FLAGS) $(MPI_LDFLAGS) 
#	$(MPE_CC) -mpitrace -o $@ $? $(LD_FLAGS) $(MPI_LDFLAGS) 
#	$(CC) -o $@ $? $(LD_FLAGS) $(MPI_LDFLAGS) 

clean:
	$(RM) -f *.o mandel-basic-mpi
