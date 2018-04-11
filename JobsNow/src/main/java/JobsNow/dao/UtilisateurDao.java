package JobsNow.dao;

import JobsNow.entities.Utilisateur;

public interface UtilisateurDao {

    public Utilisateur addUtilisateur(Utilisateur utilisateur);

    public boolean seConnecter(Utilisateur utilisateur);

}
