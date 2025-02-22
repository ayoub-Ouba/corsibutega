package bd;
import bd.BaseDonnees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class command_bd {

    public boolean ajouter_commande(Timestamp dateCommande, int idUser, int idClient) {
        String query = "INSERT INTO `commande`(`status`, `date_commande`, `date_preparation`, `date_payment`, `id_user`, `id_client`) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = BaseDonnees.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {            

            // Injection des valeurs
            stmt.setString(1, "En cours de traitement");  
            stmt.setTimestamp(2, dateCommande);              
            stmt.setTimestamp(3, null);                  
            stmt.setTimestamp(4, null);                   
            stmt.setInt(5, idUser);                       
            stmt.setInt(6, idClient);                     

            // Exécuter la requête
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;  // Retourne `true` si l'insertion a réussi

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

