SHELL		= /bin/sh
top_srcdir	= /usr/local/tempsreel/ORBACUS/

include $(top_srcdir)/MakeOB.rules


VPATH=.:classes/events:events

JVA =  Impl_is_ready.java\
	Synchronized_Any.java\
	Synchronized_Bool.java\
	PullSupplier_Impl.java\
	PullConsumer_Impl.java\
	PushSupplier_Impl.java\
	PushConsumer_Impl.java


IDLSRC = events.idl


all:: classes idl jva


jva:	$(JVA:.java=.class) 


idl:	$(IDLSRC:.idl=)
	

clean::
	rm -rf events *.ref



