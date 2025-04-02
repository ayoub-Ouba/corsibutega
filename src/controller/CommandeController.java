package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import basedonne.BaseDonnees;
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
		public void updateCommandeStatus(int id, String newStatus) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // Obtenez la connexion à la base (la méthode getConnection() est à adapter selon votre projet)
            conn = BaseDonnees.getConnection();
            String sql = "UPDATE commandes SET status = ? WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, newStatus);
            stmt.setInt(2, id);
            int rowsUpdated = stmt.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("Le statut de la commande a été mis à jour.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(stmt != null)
                    stmt.close();
                if(conn != null)
                    conn.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
