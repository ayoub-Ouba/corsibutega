package controller;
import model.Client;
import model.Commande;
import model.Produit;

import java.util.ArrayList;
import java.util.List;
public class DashbordController {
	
	public List<Integer> countClientCommandeProduit(){
		List<Integer> ls=new ArrayList<>();
		
		ls.add(Client.countClient());
		ls.add(Commande.countCommande());
		return ls;
		
	}

}
