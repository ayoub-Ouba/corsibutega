package Model;
import java.util.ArrayList;

public class produits {
	public  ArrayList<produit> produits;
	
	public produits() {
		this.produits= new ArrayList<produit>();
	}
	
	public void ajouterproduit(produit pr) {
		produits.add(pr);
		
	}

}
