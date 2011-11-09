
OB=/usr/local/tempsreel/ORBACUS
OB_CPPFLAGS =  -I$(OB)/include
OB_LDFLAGS= -L$(OB)/lib
OB_LIBS= -lIDL -lOB -lnsl -lsocket -lposix4 -lrt

COMMON_OBJS	= mo_it.o
SERVER_OBJS	= mo_it_skel.o \
		  mo_it_impl.o \
		  serveur.o
CLIENT_OBJS	= client.o

CPP_SRCS	= $(COMMON_OBJS:.o=.cpp) \
		  $(SERVER_OBJS:.o=.cpp) \
		  $(CLIENT_OBJS:.o=.cpp)

CXX=c++ 
CXXFLAGS= -g -D_REENTRANT
CPPFLAGS= -I. $(OB_CPPFLAGS) 
LDFLAGS	= $(OB_LDFLAGS) 
LIBS	= $(OB_LIBS) 


all:: serveur client

serveur: $(COMMON_OBJS) $(SERVER_OBJS)
	$(CXX) $(CXXFLAGS) $(LDFLAGS) -o $@ \
	$(COMMON_OBJS) $(SERVER_OBJS) $(LIBS)

client: $(COMMON_OBJS) $(CLIENT_OBJS)
	$(CXX) $(CXXFLAGS) $(LDFLAGS) -o $@ \
	$(COMMON_OBJS) $(CLIENT_OBJS) $(LIBS)

mo_it.cpp mo_it.h: mo_it.idl
	idl mo_it.idl

mo_it_skel.cpp mo_it_skel.h : mo_it.cpp


clean::
	rm -rf mo_it.h mo_it.cpp
	rm -rf mo_it_skel.h mo_it_skel.cpp
	rm -rf *.o serveur client *.ref




