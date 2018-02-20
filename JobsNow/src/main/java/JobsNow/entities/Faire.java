package JobsNow.entities;

import java.util.Date;

public class Faire {

    private Annonce annonce;
    private Utilisateur utilisateur;

    public Faire(){

    }

    public Faire(Annonce annonce, Utilisateur utilisateur){
        this.annonce = annonce;
        this.utilisateur = utilisateur;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
}
