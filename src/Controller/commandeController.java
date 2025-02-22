package Controller;

import java.sql.Timestamp;
import java.time.Instant;


import View.Commandeview;
import bd.command_bd;
import Model.Utilisateur;

public class commandeController {
	  private command_bd command_bd;
	    private Commandeview view;
	    private Utilisateur utilisateur;

	    public commandeController(Commandeview view,Utilisateur user) {
	        this.command_bd = new command_bd(); 
	        this.view = view;
	        this.utilisateur=user;
	    }

	    public void commander() {
	        Timestamp date_commande = Timestamp.from(Instant.now());

	    	boolean res=command_bd.ajouter_commande(date_commande,utilisateur.getid(),1);

	        if (res) {
	            view.afficherMessage("commande ajouté !");
	            
	        } else {
	            view.afficherMessage("commande non ajouté");
	        }
	    }

}
