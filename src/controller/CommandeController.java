package controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
//basededonne
import basedonne.CommandBd;
import basedonne.ProduitBd;

//model
import model.Client;
import model.Commande;
import model.Produits;
import model.Utilisateur;

//View
import view.CommandView;
import view.ProduitView;
import view.AjouterProduit;



public class CommandeController {
		  private CommandBd command_bd;
		  private int utilisateur;
		  private Client client;
		  private Commande commande;

	    public CommandeController(int utilisateur){
	        this.utilisateur=utilisateur;
	    }
	    public CommandeController(){
	       
	        
	    }
	    public boolean supprimerCommande(int id) {
	    	return command_bd.supprimerCommande( id);
	    }
	    public int commander(int idproduit,int idient,int id_utilisateur,int qunt) {
	    	//date et l'heure de commande
	    	LocalDateTime date_commande = LocalDateTime.now();
	        Commande commande=new Commande(0,"En cours",idproduit,"",qunt,0,date_commande,null,null,idient,id_utilisateur);
	        int id_commande=commande.ajouterCommande();
	    	
	       return id_commande;
	    	
	    }
	    
	    public boolean ajouterProduitAuCommande(int idcommande,int qnt,int idproduit) {	    	
	       Commande commande=new Commande(idcommande,"En cours",idproduit,"",qnt,0,null,null,null,0,0);
	       return commande.ajouterProduitauCommande();
	    	
	    }

	    public  List<Commande> commandes() {		    
	    	List<Commande> Commandes = Commande.arrayCommande();
	        if (Commandes != null) {
	            System.out.println("Commandes récupérés : " + Commandes.size());
	        } else {
	            System.out.println("Aucun Commande trouvé");
	        }
	        return Commandes;
	    } 

}
