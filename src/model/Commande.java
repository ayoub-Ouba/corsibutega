package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


import basedonne.CommandBd; 

public class Commande {
	    private String status;
	    private LocalDateTime  date_commande;
	    private LocalDateTime  date_preparation;
	    private LocalDateTime  date_payment;
	    private int id_user;
	    private int id_client ;

	    public Commande(String status,LocalDateTime date_commande,LocalDateTime date_preparation,LocalDateTime date_payment, int id_user,int id_client) {
	        this.status = status;
	        this.date_commande = date_commande;
	        this.date_preparation = date_preparation;
	        this.date_payment = date_payment;
	        this.id_user = id_user;
	        this.id_client = id_client;
	    }

	    public String getstatus() {
	        return status;
	    }
	    public LocalDateTime getdate_commande() {
	        return date_commande;
	    }
	    public LocalDateTime getdate_preparation() {
	        return date_preparation;
	    }
	    public LocalDateTime getdate_payment() {
	        return date_payment;
	    }
	    public int getid_user() {
	        return id_user;
	    }
	    public int getid_client() {
	        return id_client;
	    }
	    public static int countCommande() {
	    	CommandBd countbd=new CommandBd();
	    	return countbd.countCommande();
	    }

	   

}
