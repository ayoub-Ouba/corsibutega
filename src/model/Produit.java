package model;

import basedonne.ProduitBd;

public class Produit {
	    private int  id;
	    private String label;
	    private float  prix;
	    private int id_catg;

	    public Produit(int id,String label,float prix,int id_catg) {
	        this.id = id;
	        this.label = label;
	        this.prix = prix;
	        this.id_catg = id_catg;
	    }

	    public int getid() {
	        return id;
	    }
	    public String getlabel() {
	        return label;
	    }
	    public float getprix() {
	        return prix;
	    }
	    
	    public int getid_catg() {
	        return id_catg;
	    }
	    public String toString() {
	        // Affichage uniquement du nom et prénom
	        return label ;
	    }
	    
	    public static Produits liste_produit() {
	    	ProduitBd prdbd=new ProduitBd();
	    	return prdbd.liste_produit();
	    	
	    }
	    

}
