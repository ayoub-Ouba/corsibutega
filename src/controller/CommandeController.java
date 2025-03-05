package controller;

import java.sql.Timestamp;
import java.time.Instant;

import basedonne.Command_bd;
import basedonne.Produit_bd;
import model.Client;
import model.Produits;
import model.Utilisateur;
import view.CommandView;
import view.ProduitView;

public class CommandeController {
	  private Command_bd command_bd;
	    private CommandView view;
	    private Utilisateur utilisateur;
	    private ProduitView view_prod;
	    private Client client;

	    public CommandeController(CommandView view,Utilisateur user,Client client){
	        this.command_bd = new Command_bd(); 
	        this.view_prod=new ProduitView();
	        this.view = view;
	        this.utilisateur=user;
	        this.client=client;
	    }

	    public boolean commander(Produits produits) {
	    	//date et l'heure de commande
	        Timestamp date_commande = Timestamp.from(Instant.now());

	    	int id_comande=command_bd.ajouter_commande(date_commande,utilisateur.getid(),client.getid());
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
	    		
	    		if(Produit_bd.ajouter_prd_commande(id_comande,produit_choisi,quantiter_cmd)) {
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
