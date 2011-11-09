

setenv RACINE_OB /usr/local/ORBACUS
setenv PATH $RACINE_OB/bin:$PATH

if ($?MANPATH) then  
	setenv MANPATH $RACINE_OB/man:$MANPATH
else
	setenv MANPATH $RACINE_OB/man
endif

if ($?LD_LIBRARY_PATH) then  
	setenv LD_LIBRARY_PATH $RACINE_OB/lib:$LD_LIBRARY_PATH
else
	setenv LD_LIBRARY_PATH $RACINE_OB/lib
endif

setenv CLASSPATH $RACINE_OB/lib:.:classes:







