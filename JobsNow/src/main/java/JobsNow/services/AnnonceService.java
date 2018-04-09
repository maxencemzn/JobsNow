package JobsNow.services;

import JobsNow.dao.impl.AnnonceDaoImpl;
import JobsNow.entities.Annonce;

public class AnnonceService {

    private static class AnnonceServiceHolder {
        private static AnnonceService instance = new AnnonceService();
    }

    public static AnnonceService getInstance(){
        return AnnonceServiceHolder.instance;
    }

    private AnnonceService(){

    }

    private AnnonceDaoImpl annonceDaoImpl = new AnnonceDaoImpl();

    public Annonce addAnnonce(Annonce annonce) {
        return annonceDaoImpl.addAnnonce(annonce);
    }
}

