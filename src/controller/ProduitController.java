package controller;

import basedonne.ProduitBd;

import model.Produit;
import model.Produits;
import view.ProduitView;

public class ProduitController {
   
    public Produits liste_produit() {
    	return ProduitBd.liste_produit();
      
    } 

}
