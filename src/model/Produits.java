package model;
import java.util.ArrayList;

public class Produits {
	public  ArrayList<Produit> produits;
	
	public Produits() {
		this.produits= new ArrayList<Produit>();
	}
	
	public void ajouterproduit(Produit pr) {
		produits.add(pr);
		
	}

}
