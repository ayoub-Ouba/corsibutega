package controller;

import basedonne.ProduitBd;

import model.Produit;
import model.Produits;
import view.ProduitView;

public class ProduitController {
	private ProduitBd produit_bd;
    private Produit produit;

    public ProduitController() {
        this.produit_bd = new ProduitBd(); 


    }

    public Produits liste_produit() {
    	Produits produits=produit_bd.liste_produit();
    	return produits;
      
    } 

}
