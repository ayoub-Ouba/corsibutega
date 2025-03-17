package controller;

<<<<<<< HEAD
import basedonne.Produit_bd;
=======
<<<<<<< HEAD
import basedonne.Produit_bd;
=======
import basedonne.ProduitBd;
>>>>>>> b68b217 (Version Dashboard)
>>>>>>> 1264f539e04be219c41c47d6e04ef9954577c518
import model.Produit;
import model.Produits;
import view.ProduitView;

public class ProduitController {
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 1264f539e04be219c41c47d6e04ef9954577c518
	private Produit_bd produit_bd;
    private Produit produit;

    public ProduitController() {
        this.produit_bd = new Produit_bd(); 
<<<<<<< HEAD
=======
=======
	private ProduitBd produit_bd;
    private Produit produit;

    public ProduitController() {
        this.produit_bd = new ProduitBd(); 
>>>>>>> b68b217 (Version Dashboard)
>>>>>>> 1264f539e04be219c41c47d6e04ef9954577c518
    }

    public Produits liste_produit() {
    	Produits produits=produit_bd.liste_produit();
    	return produits;
      
    } 

}
