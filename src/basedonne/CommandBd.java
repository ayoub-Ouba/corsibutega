package basedonne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.Commande;

public class CommandBd {

    public int ajouter_commande(Timestamp dateCommande, int idUser, int idClient) {
        String query = "INSERT INTO `commande`(`status`, `date_commande`, `date_preparation`, `date_payment`, `id_user`, `id_client`) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = BaseDonnees.getConnection(); 
        	//PreparedStatement.RETURN_GENERATED_KEYS  pour recuperer le id de commande aprée l'insertion 
            PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) { 
            stmt.setString(1, "En cours de traitement");  
            stmt.setTimestamp(2, dateCommande);              
            stmt.setTimestamp(3, null);                  
            stmt.setTimestamp(4, null);                   
            stmt.setInt(5, idUser);                       
            stmt.setInt(6, idClient);                     

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) { 
                    if (generatedKeys.next()) {
                        int idCommande = generatedKeys.getInt(1);
                        return idCommande;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
    }
    public boolean modifier_status_commande(int id_commande,String status) {
        String req = "UPDATE commande SET status= ? WHERE Id_commande=?";

        try (Connection conn = BaseDonnees.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(req)) { 
	            stmt.setString(1, status);  
	            stmt.setInt(2, id_commande);
            int rowsInserted = stmt.executeUpdate();
           if(rowsInserted>0) {
        	   return true;
           }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public boolean supprimerCommande(int id) {
    	String req="DELETE FROM `commande` WHERE Id_commande=?";
    	
    	try(Connection conn=BaseDonnees.getConnection();
    		PreparedStatement stmt = conn.prepareStatement(req)){
    		stmt.setInt(1, id);
    		int rowsInserted = stmt.executeUpdate();
            if(rowsInserted>0) {
          	   return true;
             }	
    	}catch (SQLException e) {
            e.printStackTrace();
        }
    	return false;
    }
    
    public int countCommande() {
	    int countcommande = 0;
	    String query = "SELECT count(*) FROM commande";
	    
	    try (Connection conn = BaseDonnees.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        
	        try (ResultSet res = stmt.executeQuery()) {
	            if (res.next()) {
	                countcommande = res.getInt(1); // Utiliser 1 pour récupérer le premier (et unique) résultat
	            }
	        }
	        
	    } catch (SQLException e) {
	        System.err.println("Erreur lors de la récupération du nombre de commande : " + e.getMessage());
	    }
	    
	    return countcommande;
	}
    public List<Commande> arrayCommandeBD() {
	    List<Commande> commandes = new ArrayList<>();
	    String query = "SELECT commande.`Id_commande`, `status`,produit.`Id_produit`, `label`,`Quantiter_commander`,`prix`,`date_commande`, `date_preparation`, `date_payment`, `id_client` FROM `commande`,`produit`,`détaille_commande` WHERE commande.Id_commande=détaille_commande.id_commande and détaille_commande.id_produit=produit.Id_produit order by  Id_commande";
	    
	    try (Connection conn = BaseDonnees.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        
	        try (ResultSet res = stmt.executeQuery()) {
	            while (res.next()) {  
	            	String dateStr = res.getString("date_commande");
	            	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
	            	
	            	LocalDateTime dateCommande = LocalDateTime.parse(dateStr, formatter);
	            	
	            	String dateprepStr = res.getString("date_preparation");
	            	LocalDateTime date_preparation=null;
	            	if(dateprepStr!=null) {
		            	date_preparation = LocalDateTime.parse(dateprepStr, formatter);
	            	}
	            
	            	String datepayStr = res.getString("date_payment");
	            	LocalDateTime date_payment1=null;
	            	if(datepayStr!=null) {
		            	date_payment1 = LocalDateTime.parse(datepayStr, formatter);

	            	}
	            	commandes.add(new Commande(
	                    res.getInt("Id_commande"),
	                    res.getString("status"),
	                    res.getInt("Id_produit"),res.getString("label"),
	                    res.getInt("Quantiter_commander"),
	                    res.getInt("prix"),
	                    dateCommande,date_preparation,date_payment1,
	                    res.getInt("id_client")
	                ));
	            }
	        }
	        
	    } catch (SQLException e) {
	        System.err.println("Erreur lors de la récupération des commandes : " + e.getMessage());
	    }
	    
	    // Retourner la liste vide si aucun client n'est trouvé, plutôt que null
	    return commandes;
	}
}

