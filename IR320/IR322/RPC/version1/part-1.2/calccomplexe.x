
/* Declarations en RPCL du programme de calculatrice de base*/
struct complexe {
  int pentiere ;
  float pimaginaire;
 };


program CALCCOMPPROG {		    /* nom du programme                  */
    version CALCVERS {		    /* num�ro de version                 */
	int ADD(complexe)  = 1;  /* premi�re proc�dure du programme   */ 
	int MUL (complexe) = 2;  /* seconde proc�dure                 */
        void INIT (complexe) = 3;
    } = 1;			    /* num�ro de la version du programme */
} = 0x30090949;			    /* num�ro de programme               */

