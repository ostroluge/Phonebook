package main;
import java.util.List;

import domaine.Entree;
import domaine.Numero;
import fabrique.FabEntree;
import fabrique.FabNumero;

public class Main {

	public static void main(String[] args) {
			
//		FabEntree.getInstance().addEntree("BRISSE", "Teo");
		
		List<Entree> entrees = FabEntree.getInstance().getAllEntrees();
		
		for (Entree entree : entrees) {
			System.out.println(entree.toString());
		}
		
//		FabEntree.getInstance().deleteEntree("BRISSE");
//		
//		Entree entree = FabEntree.getInstance().getEntreeByName("BRISSE");
//
//		FabNumero.getInstance().addNumero("Perso", "0643262910", entree);
//		
//		FabNumero.getInstance().deleteNumero("0612344556");
//		
//		List<Numero> numeros = FabNumero.getInstance().getAllNumeros();
//		
//		for (Numero numero : numeros) {
//			System.out.println(numero.toString());
//		}
	}
}