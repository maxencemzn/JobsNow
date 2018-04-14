package JobsNow.entities;

import java.sql.Date;

public class Utilisateur {

    private Integer idUser;
    private String nom;
    private String prenom;
    private String tel;
    private String email;
    private String mdp;

    public Utilisateur(){

    }

    public Utilisateur(Integer idUser, String nom, String prenom, String tel, String email, String mdp) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.email = email;
        this.mdp = mdp;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }



    public Integer getIdUser() {
        return idUser;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return mdp;
    }
}
