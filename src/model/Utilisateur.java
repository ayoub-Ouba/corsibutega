package model;

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

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public String getNomComplet() {
        return nomComplet;
    }
}
