package JobsNow.entities;

import java.sql.Date;

public class Annonce {

    private Integer idAnnonce;
    private String titre;
    private String description;
    private Date dateDebut;
    private String lieu;
    private Boolean formation;
    private String tenue;
    private Double remuneration;

    public  Annonce(Integer idAnnonce, String titre, String description, Date dateDebut, String lieu, Boolean formation, String tenue, Double remuneration){
        this.idAnnonce = idAnnonce;
        this.titre = titre;
        this.description = description;
        this.dateDebut = dateDebut;
        this.lieu = lieu;
        this.formation = formation;
        this.tenue = tenue;
        this.remuneration = remuneration;

    }

    public void setIdAnnonce(Integer idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setFormation(Boolean formation) {
        this.formation = formation;
    }

    public void setTenue(String tenue) {
        this.tenue = tenue;
    }

    public void setRemuneration(Double remuneration) {
        this.remuneration = remuneration;
    }

    public Integer getIdAnnonce() {
        return idAnnonce;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public String getLieu() {
        return lieu;
    }

    public Boolean getFormation() {
        return formation;
    }

    public String getTenue() {
        return tenue;
    }

    public Double getRemuneration() {
        return remuneration;
    }
}
