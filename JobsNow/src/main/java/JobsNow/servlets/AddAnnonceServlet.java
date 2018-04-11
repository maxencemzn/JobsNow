package JobsNow.servlets;

import JobsNow.entities.Annonce;
import JobsNow.services.AnnonceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/addAnnonce")
public class AddAnnonceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String titre = req.getParameter("titre");
        String description = req.getParameter("description");
        Date dateDebut = Date.valueOf(req.getParameter("date"));
        String lieu = req.getParameter("lieu");
        String formation = req.getParameter("formation");
        String tenue = req.getParameter("tenue");
        Double remuneration = Double.valueOf(req.getParameter("remuneration"));

        Annonce annonce = new Annonce(null, titre, description, dateDebut, lieu, formation, tenue, remuneration);
        AnnonceService.getInstance().addAnnonce(annonce);

        resp.sendRedirect("Administration");

    }
}
