package model;

import java.time.LocalDateTime;
import java.util.List;
import java.sql.Timestamp;
import java.time.*;

import basedonne.CommandBd;
import basedonne.ProduitBd; 

public class Commande {
		private int id;
	    private String status;
	    private String label;
	    private int id_produit;
	    private int Quantiter_commander;
	    private float prix;
	    private LocalDateTime  date_commande;
	    private LocalDateTime  date_preparation;
	    private LocalDateTime  date_payment;
	    private int id_user;
	    private int id_client ;

	    public Commande(int id,String status,int id_produit,String label,int Quantiter_commander,float prix,LocalDateTime date_commande,LocalDateTime date_preparation,LocalDateTime date_payment,int id_client,int id_user) {
	        this.id=id;
	    	this.status = status;
	    	this.label=label;
	    	this.id_produit=id_produit;
	    	this.Quantiter_commander=Quantiter_commander;
	    	this.prix=prix*Quantiter_commander;
	        this.date_commande = date_commande;
	        this.date_preparation = date_preparation;
	        this.date_payment = date_payment;
	        this.id_client = id_client;
	        this.id_user=id_user;
	    }
	    public int getid() {
	        return id;
	    }
	    public void setid(int id) {
	        this.id=id;
	    }
	    public String getstatus() {
	        return status;
	    }
	    public void setstatus(String status) {
	        this.status=status;
	    }
	    public String getlabel() {
	        return label;
	    }
	    public void setlabel(String label) {
	        this.label=label;
	    }
	    public float getprix() {
	        return prix;
	    }
	    public void setprix(float prix) {
	        this.prix=prix;
	    }
	    public int getid_produit() {
	        return id_produit;
	    }
	    public void setid_produit(int id_produit) {
	        this.id_produit=id_produit;
	    }
	    public int getQuantiter_commander() {
	        return Quantiter_commander;
	    }
	    public void setQuantiter_commander(int Quantiter_commander) {
	        this.Quantiter_commander=Quantiter_commander;
	    }
	    public LocalDateTime getdate_commande() {
	        return date_commande;
	    }
	    public void setdate_commande( LocalDateTime dc) {
	        this.date_commande=dc;
	    }
	    public LocalDateTime getdate_preparation() {
	    		return date_preparation;
	    }
	    public void setdate_preparation( LocalDateTime dpr) {
	        this.date_preparation=dpr;
	    }
	    public LocalDateTime getdate_payment() {
	        return date_payment;
	    }
	    public void setdate_payment( LocalDateTime dpy) {
	        this.date_payment=dpy;
	    }
	    public int getid_user() {
	        return id_user;
	    }
	    public int getid_client() {
	        return id_client;
	    }
	    public int ajouterCommande() {
	    	CommandBd coumtbd=new CommandBd();
	    	return coumtbd.ajouter_commande(date_commande,status,id_user,id_client,Quantiter_commander,id_produit);
	    }
	    public boolean ajouterProduitauCommande() {	    	
	    	return ProduitBd.ajouter_prd_commande(id,id_produit,Quantiter_commander);
	    }
	    public static int countCommande() {
	    	CommandBd countbd=new CommandBd();
	    	return countbd.countCommande();
	    }
	    

	    public static List<Commande> arrayCommande() {
	    	CommandBd countbd=new CommandBd();
	    	return countbd.arrayCommandeBD();
	    }
	   

}
