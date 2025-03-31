package controller;
import java.util.List;


import model.Client;
import view.DashboardView;
import view.AddClient;

public class ClientControleer {
	   private DashboardView view;
	   private Client client;

	    public ClientControleer() {
	    	//client_bd=new ClientBd();
		}

	    public Client addClient( String nom,String prenom,String tele, String email,AddClient addClientView) {
	       this.client=new Client(0,nom,prenom,tele,email);// Création d'un objet client avec ID temporaire (0)
	       int idClient=client.addClient();// Ajout du client à la base de données et récupération de son ID
	       if(idClient!=0) {
	    	System.out.println("le client est bien ajouté");
	    	client.setid(idClient);
	    	 addClientView.dispose(); // Fermer le popup si l'ajout réussit
	    	 
	    	return client;
	     }else {
	    	 System.out.println("Problème lors de l'ajout du client.");
	    	 addClientView.setVisible(false);
	    }
	 return null;
	  }
	    
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
	    
	    	List<Client> clients = Client.arrayClient();// Récupération de la liste des clients
	        if (clients != null && this.view!=null) {
	            System.out.println("Clients récupérés : " + clients.size());
	        } else {
	            System.out.println("Aucun client trouvé");
	        }
	        return clients;
	    }

}
