package Controller;

import java.sql.Timestamp;
import java.time.Instant;


import View.Commandeview;
import bd.command_bd;
import Model.Utilisateur;
import Model.produits;
import View.produitView;
import bd.produit_bd;

public class commandeController {
	  private command_bd command_bd;
	    private Commandeview view;
	    private Utilisateur utilisateur;
	    private produitView view_prod;

	    public commandeController(Commandeview view,Utilisateur user){
	        this.command_bd = new command_bd(); 
	        this.view_prod=new produitView();
	        this.view = view;
	        this.utilisateur=user;
	    }

	    public boolean commander(produits produits) {
	    	//date et l'heure de commande
	        Timestamp date_commande = Timestamp.from(Instant.now());

	    	int id_comande=command_bd.ajouter_commande(date_commande,utilisateur.getid(),1);
	    	//varyable boolean ajouter_produit
	    	boolean Ajp=true;
	    	while(Ajp) {
	    		//afficher les information des produits
	    		for(int i=0;i<produits.produits.size();i++) {
	    			produits.produits.get(i).information();;
	    		}
	    		// Entrer le produit + la quantiter 
	    		int produit_choisi=view_prod.getInputQ("choisi le id de produit ");
	    		int quantiter_cmd=view_prod.getInputQ("la quantiter commander");
	    		
	    		if(produit_bd.ajouter_prd_commande(id_comande,produit_choisi,quantiter_cmd)) {
	    			System.out.println("le produit est ajouté");
	    		}
	    		String rep =view.getInput("vous voulez ajouter un autre produit t(oui)/f(non)");
	    		if (rep.equals("t")) {
	    		    Ajp = true;
	    		} else if (rep.equals("f")) {
	    		    Ajp = false;
	    		    System.out.println("votre commande est bien  ajouté");
	    		} else {
	    			System.out.println("votre commande est bien  ajouté");
	    		    return false;
	    		}

	    	}
	    	return true;

	      
	    }

}
