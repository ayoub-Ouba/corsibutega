package basedonne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Command_bd {

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
}

