
SHELL		= /bin/sh
top_srcdir	= /usr/local/tempsreel/ORBACUS/

include $(top_srcdir)/MakeOB.rules

VPATH=.:classes/mon_module:mon_module

IDLSRC = mo_it.idl
CLI = Client.java 

all::	classes idl $(CLI:.java=.class) 
	

idl::
	jidl $(IDLSRC)

clean::
	rm -rf mon_module *.ref







