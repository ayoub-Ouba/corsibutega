package controller;

import java.sql.Timestamp;
import java.time.Instant;

<<<<<<< HEAD
import basedonne.Command_bd;
import basedonne.Produit_bd;
=======
<<<<<<< HEAD
import basedonne.Command_bd;
import basedonne.Produit_bd;
=======
import basedonne.CommandeBd;
import basedonne.ProduitBd;
>>>>>>> b68b217 (Version Dashboard)
>>>>>>> 1264f539e04be219c41c47d6e04ef9954577c518
import model.Client;
import model.Produits;
import model.Utilisateur;
import view.CommandView;
import view.ProduitView;

public class CommandeController {
<<<<<<< HEAD
	  private Command_bd command_bd;
=======
<<<<<<< HEAD
	  private Command_bd command_bd;
=======
	  private CommandeBd command_bd;
>>>>>>> b68b217 (Version Dashboard)
>>>>>>> 1264f539e04be219c41c47d6e04ef9954577c518
	    private CommandView view;
	    private Utilisateur utilisateur;
	    private ProduitView view_prod;
	    private Client client;

	    public CommandeController(CommandView view,Utilisateur user,Client client){
<<<<<<< HEAD
	        this.command_bd = new Command_bd(); 
=======
<<<<<<< HEAD
	        this.command_bd = new Command_bd(); 
=======
	        this.command_bd = new CommandeBd(); 
>>>>>>> b68b217 (Version Dashboard)
>>>>>>> 1264f539e04be219c41c47d6e04ef9954577c518
	        this.view_prod=new ProduitView();
	        this.view = view;
	        this.utilisateur=user;
	        this.client=client;
	    }

<<<<<<< HEAD
	    public boolean supprimerCommande(int id) {
	    	return command_bd.supprimerCommande( id);
	    }
=======
>>>>>>> 1264f539e04be219c41c47d6e04ef9954577c518
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
	    		
<<<<<<< HEAD
	    		if(Produit_bd.ajouter_prd_commande(id_comande,produit_choisi,quantiter_cmd)) {
=======
<<<<<<< HEAD
	    		if(Produit_bd.ajouter_prd_commande(id_comande,produit_choisi,quantiter_cmd)) {
=======
	    		if(ProduitBd.ajouter_prd_commande(id_comande,produit_choisi,quantiter_cmd)) {
>>>>>>> b68b217 (Version Dashboard)
>>>>>>> 1264f539e04be219c41c47d6e04ef9954577c518
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
