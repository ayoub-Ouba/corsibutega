package controller;
import model.Client;
import model.Commande;

import java.util.ArrayList;
import java.util.List;
public class DashbordController {

	private List<Client> clients;
    private List<Commande> commandes;

    // Constructeur pour injecter les données
    public DashbordController(List<Client> clients, List<Commande> commandes) {
        this.clients = clients;
        this.commandes = commandes;
    }

    // Retourne le nombre de clients
    public int getNombreClients() {
        return clients != null ? clients.size() : 0;
    }

    // Retourne le nombre de commandes
    public int getNombreCommandes() {
        return commandes != null ? commandes.size() : 0;
    }
	
	// public List<Integer> countClientCommandeProduit(){
	// 	List<Integer> ls=new ArrayList<>();
		
	// 	ls.add(Client.countClient());
	// 	ls.add(Commande.countCommande());
	// 	return ls;
		
	// }

}
