import View.loginview;
import View.Commandeview;
import View.clientView;
import Model.Utilisateur;
import Controller.LoginController;
import Controller.commandeController;
import Controller.ClientControleer;
import Model.Client;
import Controller.produitController;
import Model.produits;
import View.produitView;
public class Main {

	    public static void main(String[] args) {
	    	loginview view = new loginview();
	    	LoginController controller = new LoginController(view);
	    	Utilisateur user_connecter =controller.login();
	    	if(user_connecter!=null) {
	      
		       clientView view_client = new clientView();
		       ClientControleer controller_client = new ClientControleer(view_client);
		        //ajouter Client 
		       //Client client=controller_client.ajouter_client();
		        //trouver client
		        Client client=controller_client.find_client();
		        if(client!=null) {
		        
	    		
	 	        produitController produit_controller=new produitController();
	 	        //liste produits
	 	        produits produits=produit_controller.liste_produit();
		 	        
		        //ajouter une commande 
			      Commandeview view_commande = new Commandeview();
			      commandeController controller_commande = new commandeController(view_commande,user_connecter,client);
			      controller_commande.commander(produits);

		        }
	    }
	   }



}
