package JobsNow.entities;

import java.sql.Date;

public class Utilisateur {

    private Integer idUser;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String adresse;
    private String tel;
    private String email;
    private String mdp;
    private Integer admin;

    public Utilisateur(){

    }

    public Utilisateur(Integer idUser, String nom, String prenom, Date dateNaissance, String adresse, String tel, String email, String mdp, Integer admin) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
        this.mdp = mdp;
        this.admin = admin;
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

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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

    public void setAdmin(Integer admin) {
        this.admin = admin;
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

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getAdresse() {
        return adresse;
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

    public Integer getAdmin() {
        return admin;
    }
}
