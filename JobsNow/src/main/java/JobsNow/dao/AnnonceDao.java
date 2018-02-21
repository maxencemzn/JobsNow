package JobsNow.dao;

import JobsNow.entities.Annonce;
import JobsNow.entities.Utilisateur;

public interface AnnonceDao {
    public Utilisateur addAnnonce(Annonce annonce);
}
