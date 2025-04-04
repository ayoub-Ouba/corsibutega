package controller;
import java.util.List;

import model.Client;
import view.AddClient;
import view.ClientView;

public class ClientControleer {
	   private Client client;

	    public ClientControleer() {
		}

    public Client ajouterClient(String nom,String prenom,String tele, String email,AddClient addClientView) {
       this.client=new Client(0,nom,prenom,tele,email);
       int idClient=client.ajouterClient();
       if(idClient!=0) {
	    	System.out.println("le client est bien ajouté");
	    	client.setid(idClient);
	    	//fermer la poppup client
	    	 addClientView.dispose(); 
	    	return client;
	     }else {
	    	 addClientView.dispose();
	    	 ClientView clv=new ClientView();
	    	 clv.showMessage("il y a un probleme le client n'est pas ajouter");
	    }
       return null;
  }

    public static List<Client> clients() {
    	List<Client> clients = Client.listeClient();
        if (clients != null ) {
            System.out.println("Clients récupérés : " + clients.size());
        } else {
            System.out.println("Aucun client trouvé");
        }
        return clients;
    }
    
    public static int nombreClient() {
        return Client.nombreClient();
    }

}
