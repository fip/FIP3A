SHELL		= /bin/sh
top_srcdir	= /usr/local/tempsreel/ORBACUS


include $(top_srcdir)/MakeOB.rules


VPATH=.:classes/tpcorba:tpcorba

CLI = Client.java 
SRV = Calculatrice.java calcul_Impl.java usine_Impl.java Serveur.java
IDLSRC = tpcorba.idl


all:: classes idl cli srv


cli:	$(CLI:.java=.class) 

srv:	$(SRV:.java=.class) 

idl:	$(IDLSRC:.idl=)
	

clean::
	rm -rf tpcorba *.ref



