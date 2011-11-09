/*
 * Auteurs	: Yohann Lepage & Jonathan Morfin
 * Date		: 20/10/2011
 * Version	: 1.0 
 * Module	: FIP3A IR322 TP RPC
 */

#include "rdict.h"


void
rdictprog_1(char *host)
{
    
    CLIENT *clnt;
    int *result_1;
    char *initw_1_arg;
    int *result_2;
    char * insertw_1_arg;
    int *result_3;
    char * deletew_1_arg;
    int *result_4;
    char * lookupw_1_arg;
    char **ptrPtrWord;
    char word[MAXWORD+1];
    char cmd;
    int wrdlen; /* length of input word */
    
    
    // Allocation de la mémoire
    ptrPtrWord = (char**) malloc(sizeof(char)*(MAXWORD+1));
    if (ptrPtrWord==NULL)
    	printf("Malloc error");
    
    #ifndef DEBUG
    clnt = clnt_create (host, RDICTPROG, RDICTVERS, "udp");
    if (clnt == NULL) {    
        clnt_pcreateerror (host);
        exit (1);
    }
    #endif /* DEBUG */
    
    
    while (1) {
        
        wrdlen = nextin(&cmd, word);
        if (wrdlen < 0)
        exit(0);
        word[wrdlen] = '\0';
        
        *ptrPtrWord=word;
        
        switch (cmd) {
            
            // Initialisation
            case 'I':  
            	result_1 = initw_1((void*)word, clnt);
            	if (result_1 == (int *) NULL) {
              	  clnt_perror (clnt, "call failed");
            	}
            	else if(*result_1 == 1){
               	 printf("Dictionary initialized to empty.\n");
            	}
            	else{
               	 printf("Unknown return code %d", (int)*result_3);
            	}
            	
            // Insertion
            break;
            case 'i':  
            	result_2=insertw_1((char**)ptrPtrWord, clnt);
            	if (result_2 == (int *) NULL) {
               	 clnt_perror (clnt, "call failed");
            	}
            	else{
              	  printf("%s inserted. Nb words dictionary=%d\n",word,(int)*result_2);
            	}
            
            // Suppression
            break;
            case 'd':  
            	result_3 = deletew_1((char**)ptrPtrWord, clnt);
	            if (result_3 == (int *) NULL) {
        	        clnt_perror (clnt, "call failed");
            	}
	            else if(*result_3 == 1){
        	        printf("%s deleted.\n",word);
           	 }
	            else if(*result_3 == 0){
       	    		printf("%s not found\n",word);
            	}
            	else{
                	printf("Unknown return code %d", (int)*result_3);
            	}
            
            // Recherche
            break;
            case 'l': 
             
 	    		result_4 = lookupw_1((char**)ptrPtrWord, clnt);
        		if (result_4 == (int *) NULL) {
            	   	clnt_perror (clnt, "call failed");
           		}
				else if (*result_4==1){
              		printf("%s was found.\n",word);
            	}
            	else if(*result_4==0){
                	printf("%s was not found.\n",word);
            	}
            	else{
                	printf("Unknown return code: %d", (int)*result_3);
            	}
            
            break;
            case 'q':  /* quit */
            	printf("program quits.\n");
            	exit(0);
            default: /* illegal input */
            	printf("command %c invalid.\n", cmd);
            break;
            
        }
        
        
    }
    
    
    
    
    
    
    
    
    
    
    free(ptrPtrWord);
    #ifndef DEBUG
    clnt_destroy (clnt);
    #endif /* DEBUG */
    
}



int
main (int argc, char *argv[])
{
    
    char *host;
    
    if (argc < 2) {
        
        printf ("usage: %s server_host\n", argv[0]);
        exit (1);
        
    }
    
    host = argv[1];
    rdictprog_1 (host);
    exit (0);
    
}






/*------------------------------------------------------------------------
* nextin - read a command and (possibly) a word from the next input line
*------------------------------------------------------------------------
*/
int
nextin(char *cmd, char *word)
{
    
    int i, ch;
    
    ch = getc(stdin);
    while (isspace(ch))
    ch = getc(stdin);
    if (ch == EOF)
    return -1;
    *cmd = (char) ch;
    ch = getc(stdin);
    while (isspace(ch))
    ch = getc(stdin);
    if (ch == EOF)
    return -1;
    if (ch == '\n')
    return 0;
    i = 0;
    while (!isspace(ch)) {
        
        if (++i > MAXWORD) {
            
            printf("error: word too long.\n");
            exit(1);
            
        }
        
        *word++ = ch;
        ch = getc(stdin);
        
    }
    
    return i;
    
}
