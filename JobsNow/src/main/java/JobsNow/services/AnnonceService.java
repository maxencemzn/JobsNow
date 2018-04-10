package JobsNow.services;

import JobsNow.dao.AnnonceDao;
import JobsNow.dao.impl.AnnonceDaoImpl;
import JobsNow.entities.Annonce;

import java.util.List;

public class AnnonceService {

    private static class AnnonceServiceHolder {
        private static AnnonceService instance = new AnnonceService();
    }

    public static AnnonceService getInstance(){
        return AnnonceServiceHolder.instance;
    }

    private AnnonceService(){

    }

    private AnnonceDaoImpl annonceDao = new AnnonceDaoImpl();

    public Annonce addAnnonce(Annonce annonce) {
        return annonceDao.addAnnonce(annonce);
    }

    public List<Annonce> listAnnonce() { return annonceDao.listAnnonce(); }

    public void delAnnonce(Integer idAnnonce) { annonceDao.delAnnonce(idAnnonce); }
}

