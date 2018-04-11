package JobsNow.servlets;

import JobsNow.entities.Utilisateur;
import JobsNow.services.UtilisateurService;

import java.math.BigInteger;
import java.security.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/addUtilisateur")
public class AddUtilisateurServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        Date dateNaissance = Date.valueOf(req.getParameter("dateNaissance"));
        String adresse = req.getParameter("adresse");
        String tel = req.getParameter("tel");
        String email = req.getParameter("email");
        String mdpph = req.getParameter("mdp");
        byte[] bytesOfMessage = mdpph.getBytes("UTF-8");
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] thedigest = md.digest(bytesOfMessage);
        BigInteger bigInt = new BigInteger(1, thedigest);
        String mdp = bigInt.toString(16);
        while (mdp.length() < 32) {
            mdp = "0" + mdp;
        }

        Utilisateur utilisateur = new Utilisateur(null, nom, prenom, dateNaissance, adresse, tel, email, mdp, 0);
        UtilisateurService.getInstance().addUtilisateur(utilisateur);

        resp.sendRedirect("JobsNow");
    }
}
