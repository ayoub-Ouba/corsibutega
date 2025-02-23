package Controller;

import Model.produit;
import View.produitView;
import bd.produit_bd;
import Model.produits;

public class produitController {
	private produit_bd produit_bd;
    private produit produit;

    public produitController() {
        this.produit_bd = new produit_bd(); 
    }

    public produits liste_produit() {
    	produits produits=produit_bd.liste_produit();
    	return produits;
      
    } 

}
