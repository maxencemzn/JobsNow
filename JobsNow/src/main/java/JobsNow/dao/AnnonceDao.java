package JobsNow.dao;

import JobsNow.entities.Annonce;

import java.util.List;

public interface AnnonceDao {

    public Annonce addAnnonce(Annonce annonce);

    public List<Annonce> listAnnonce();

    public void delAnnonce(Integer idAnnonce);
}
