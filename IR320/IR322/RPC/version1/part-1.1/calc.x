
/* Declarations en RPCL du programme de calculatrice de base*/


program CALCPROG {		    /* nom du programme                  */
    version CALCVERS {		    /* numéro de version                 */
	int ADD(int)  = 1;  /* premiére procédure du programme   */ 
	int MUL (int) = 2;  /* seconde procédure                 */
        void INIT (int) = 3;
    } = 1;			    /* numéro de la version du programme */
} = 0x30090949;			    /* numéro de programme               */

