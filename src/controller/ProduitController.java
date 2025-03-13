package controller;

<<<<<<< HEAD
import basedonne.Produit_bd;
=======
import basedonne.ProduitBd;
>>>>>>> b68b217 (Version Dashboard)
import model.Produit;
import model.Produits;
import view.ProduitView;

public class ProduitController {
<<<<<<< HEAD
	private Produit_bd produit_bd;
    private Produit produit;

    public ProduitController() {
        this.produit_bd = new Produit_bd(); 
=======
	private ProduitBd produit_bd;
    private Produit produit;

    public ProduitController() {
        this.produit_bd = new ProduitBd(); 
>>>>>>> b68b217 (Version Dashboard)
    }

    public Produits liste_produit() {
    	Produits produits=produit_bd.liste_produit();
    	return produits;
      
    } 

}
