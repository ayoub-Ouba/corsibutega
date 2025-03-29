package model;

import basedonne.UtilisateurBD;
public class Utilisateur {
    private int id;
    private String email;
    private String password;
    private String type;
    private String nomComplet;

    public Utilisateur(int id, String email, String password, String type, String nomComplet) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.type = type;
        this.nomComplet = nomComplet;
    }
    public Utilisateur() {
    }
    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email_v) {
        email=email_v;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password_v) {
    	password=password_v;
    }

    public String getType() {
        return type;
    }

    public String getNomComplet() {
        return nomComplet;
    }
    
    public Utilisateur getInformation() {
    	UtilisateurBD utilis_bd=new UtilisateurBD();
    	 Utilisateur utilisateur = utilis_bd.getInformationFromAnEmail(email);
    	 return utilisateur;
    }
}
