CC                   = /usr/bin/mpicc
MPE_CC               = /usr/local/mpe/bin/mpecc
MPI_CFLAGS           = 
MPI_LDFLAGS          = 
CFLAGS               = 
LDFLAGS              = 

RM = rm

srcdir = .
VPATH=.:$(srcdir)

#
PROGRAM   = mpi-ring		# name of the binary
SRCS      = mpi-ring.c		# source file
OBJS      = $(SRCS:.c=.o)	# object file

# Rules

.SUFFIXES: .c .o

.c.o: 
	$(CC) $(CFLAGS) $(MPI_CFLAGS) -c $<

# Targets

.PHONY: clean log trace std

$(PROGRAM) log: $(OBJS)
	$(MPE_CC) -mpilog -o $(PROGRAM) $? $(LD_FLAGS) $(MPI_LDFLAGS) 

trace: $(OBJS)
	$(MPE_CC) -mpitrace -o $(PROGRAM) $? $(LD_FLAGS) $(MPI_LDFLAGS) 

std: $(OBJS)
	$(CC) -o $(PROGRAM) $? $(LD_FLAGS) $(MPI_LDFLAGS) 

clean:
	$(RM) -f $(OBJS) $(PROGRAM)
