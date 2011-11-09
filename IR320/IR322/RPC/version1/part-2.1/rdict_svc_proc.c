/*
 * Auteurs	: Yohann Lepage & Jonathan Morfin FIP3A
 * Date		: 20/10/2011
 * Version	: 1.0
 * Module	: FIP3A IR322 TP RPC
 */

#include "rdict.h"

#define	MAXWORD	50		/* maximum length of a command or word	*/
#define DICTSIZ 100		/* maximum number of entries in diction*/

char dict[DICTSIZ][MAXWORD+1];/* storage for a dictionary of words */
int nwords = 0; /* number of words in the dictionary */


int *
initw_1_svc(void *argp, struct svc_req *rqstp)
{
    
    static int result;
    
    nwords = 0;
    result=1;
    printf("Init done \n");
    
    return &result;
    
}


// This function inserts word in the dictionary
int *
insertw_1_svc(char **argp, struct svc_req *rqstp)
{
    
    static int result;
    strcpy(dict[nwords], (char*)*argp); /* store word(s) in the dictionary */
    printf("Word N.%d: %s inserted in dictionary\n", nwords, *argp); /* confirmation message */
    nwords++;
    result = nwords;
    
    return &result;
    
}


// This function delete word from dictionary
int *
deletew_1_svc(char **argp, struct svc_req *rqstp)
{
    
    static int result=0;
    int i;
    
    result=0 ;
    for (i=0 ; i<nwords ; i++){
        
        // Search the word
        if (strcmp((char*)*argp, dict[i]) == 0) {
            
            nwords--;
            strcpy(dict[i], dict[nwords]); /* Replace the word to delete by the last entry in the dictionnary */
            result=1;
            printf("Word : %s deleted from dictionary\n", *argp); /* confirmation message */
            break;
            
        }
        
        
    }
    
    if (result==0)
    printf("Word %s is absent from dictionary\n", *argp); /* Error message */
    
    
    return &result;
    
}


// This function look for a word in the dictionary
int *
lookupw_1_svc(char **argp, struct svc_req *rqstp)
{
    
    static int result=0;
    int i;
    
    result=0;
    for (i=0 ; i<nwords ; i++){
        
        // Search the word
        if (strcmp(*argp, dict[i]) == 0){
            
            result=1;
            printf("Word %s found in the dictionary\n", *argp);
            break;
            
        }
        
        
    }
    
    
    if (result==0)
    printf("Word %s is absent from dictionary\n", *argp); /* Error message */
    
    return &result;
    
}
