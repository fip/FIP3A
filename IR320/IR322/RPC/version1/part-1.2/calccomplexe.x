
/* Declarations en RPCL du programme de calculatrice de base*/
struct complexe {
  int pentiere ;
  float pimaginaire;
 };


program CALCCOMPPROG {		    /* nom du programme                  */
    version CALCVERS {		    /* numéro de version                 */
	int ADD(complexe)  = 1;  /* premiére procédure du programme   */ 
	int MUL (complexe) = 2;  /* seconde procédure                 */
        void INIT (complexe) = 3;
    } = 1;			    /* numéro de la version du programme */
} = 0x30090949;			    /* numéro de programme               */

