package Controller;

import org.mindrot.jbcrypt.BCrypt;

import Model.Client;
import View.clientView;
import bd.client_bd;

public class ClientControleer {
	   private client_bd client_bd;
	    private clientView view;

	    public ClientControleer(clientView view) {
	        this.client_bd = new client_bd(); 
	        this.view = view;
	    }

	    public void ajouter_client() {
	        String prenom = view.getInput("Entrez le prenom de client :");
	        String nom = view.getInput("Entrez le enom de client :");
	        String tele = view.getInput("Entrez le numero de telephone du client :");
	        String email = view.getInput("Entrez l'email de client :");

	        if(client_bd.ajouter_client(nom,prenom,tele,email)) {
	        	view.afficherMessage("le client est bien ajouté");
	        	
	        }else {
	        	view.afficherMessage("il y a un probleme le client n'est pas ajouter");
	        }
	        
	    }
	    
	    public Client find_client() {
	        String prenom = view.getInput("Entrez le prenom de client chercher :");
	        String nom = view.getInput("Entrez le nom de client chercher :");
	        

	        if(client_bd.find_client(nom,prenom)!=null) {
	        	
	        	view.afficherMessage("le client est ce trouve");
	        	return client_bd.find_client(nom,prenom);
	        	
	        }else {
	        	view.afficherMessage("Il y a pas client avec ce nom et prenom ");
	        }
	        return null;
	        
	    }
}
