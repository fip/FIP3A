
package tpcorba.exo3;

import org.omg.CORBA.*;
import java.lang.*;


public class compteImpl extends comptePOA

	{
	private long numero_compte;
	private string titulaire;
	private double solde;
	
	public compteImpl(String t, int num)
	{
	this.t = "Toto" ;
	this.num = 0 ;
	}

	

	public void nombre_operations(long nombre) {
		System.out.println("Invocation de nombreOperation()");
		
	}

	public void debiter(double montant) {
		System.out.println("Invocation de debiter()");
		this.solde = this.solde - montant ;
	}
	
	public void crediter(double montant) {
		System.out.println("Invocation de crediter()");
		this.solde = this.solde + montant;
	}
	
	public void prelevement(double montant,compteHolder destination) {
		System.out.println("Invocation de prelevement()");
		this.debiter(montant);
		destination.crediter(montant);
		
	}
}

