
/* Declarations en RPCL du programme de calculatrice de base*/


program CALCPROG {		    /* nom du programme                  */
    version CALCVERS {		    /* num�ro de version                 */
	int ADD(int)  = 1;  /* premi�re proc�dure du programme   */ 
	int MUL (int) = 2;  /* seconde proc�dure                 */
        void INIT (int) = 3;
    } = 1;			    /* num�ro de la version du programme */
} = 0x30090949;			    /* num�ro de programme               */

