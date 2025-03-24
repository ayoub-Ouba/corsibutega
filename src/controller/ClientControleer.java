package controller;
import java.util.ArrayList;
import java.util.List;

import basedonne.ClientBd;
import model.Client;
import view.ClientView;
import view.DashboardView;
public class ClientControleer {
	   private ClientBd client_bd;
	    private DashboardView view;

	    public ClientControleer() {
	        this.client_bd = new ClientBd(); 
	       
		}

	    //public Client ajouter_client() {
	        //String prenom = view.getInput("Entrez le prenom de client :");
	       // String nom = view.getInput("Entrez le nom de client :");
	       // String tele = view.getInput("Entrez le numero de telephone du client :");
	    //String email = view.getInput("Entrez l'email de client :");
	    // int id_client=client_bd.ajouter_client(nom,prenom,tele,email);
	        // if(id_client!=0) {
	    // 	view.afficherMessage("le client est bien ajouté");
	    //	return new Client(id_client,nom,prenom,tele,email);
	        	
	    //  }else {
	        	//  	view.afficherMessage("il y a un probleme le client n'est pas ajouter");
	        	
	    // }
	    // return null;
	    //  }
	    
	    // public Client find_client() {
	    //    String prenom = view.getInput("Entrez le prenom de client chercher :");
	        //    String nom = view.getInput("Entrez le nom de client chercher :");
	        
	    //  if(client_bd.find_client(nom,prenom)!=null) {
	        	
	    //   	view.afficherMessage("le client est ce trouve");
	        	//  	return client_bd.find_client(nom,prenom);
	        	
	    //  }else {
	    //  	view.afficherMessage("Il y a pas client avec ce nom et prenom ");
	        	//  	// }
	    //  return null;
	        
	    // }
	    
	    public List<Client> clients() {
	        List<Client> clients = client_bd.liste_clients();  // Récupère la liste des clients depuis la base de données
	        if (clients != null && this.view!=null) {
	            System.out.println("Clients récupérés : " + clients.size());
	        } else {
	            System.out.println("Aucun client trouvé");
	        }
	        return clients;
	    }

}
