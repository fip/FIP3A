package tpcorba.exo2;

/**
 *	Generated from IDL interface "calcul"
 *	@author JacORB IDL compiler V 2.2, 7-May-2004
 */


public interface calculOperations
{
	/* constants */
	/* operations  */
	double memoire();
	void diviseMemoire(double donnee) throws tpcorba.exo2.divisionParZero;
	void incrementer(org.omg.CORBA.IntHolder data);
	void decrementer(org.omg.CORBA.IntHolder data);
	void ajouteMemoire(double memoire);
	void multiplieMemoire(double memoire);
}
