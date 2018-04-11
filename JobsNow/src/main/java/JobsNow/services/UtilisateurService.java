package JobsNow.services;

import JobsNow.dao.impl.UtilisateurDaoImpl;
import JobsNow.entities.Utilisateur;

import javax.rmi.CORBA.Util;
import java.sql.Date;

public class UtilisateurService {

    private static class UtilisateurServiceHolder {
        private static UtilisateurService instance = new UtilisateurService();
    }

    public static UtilisateurService getInstance(){
        return UtilisateurServiceHolder.instance;
    }

    private UtilisateurService(){

    }

    private UtilisateurDaoImpl utilisateurDaoImpl = new UtilisateurDaoImpl();

    public Utilisateur addUtilisateur(Utilisateur utilisateur) {
        return utilisateurDaoImpl.addUtilisateur(utilisateur);
    }

    public boolean SeConnecter(Utilisateur utilisateur){
        return utilisateurDaoImpl.seConnecter(utilisateur);
    }
}

