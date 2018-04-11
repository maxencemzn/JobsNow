package JobsNow.servlets;

import JobsNow.entities.Utilisateur;
import JobsNow.services.UtilisateurService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.rmi.CORBA.Util;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {

    protected TemplateEngine createTemplateEngine(HttpServletRequest request) {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(request.getServletContext());
        templateResolver.setPrefix("WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("utilisateur") == null) {
            TemplateEngine engine = this.createTemplateEngine(req);
            engine.process("Page6.SeConnecter", new WebContext(req, resp, getServletContext()), resp.getWriter());
        } else {
            resp.sendRedirect("Administration");
        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        Utilisateur utilisateur = new Utilisateur(null, null, null, null, null, null, email, mdp, null);

        if (UtilisateurService.getInstance().SeConnecter(utilisateur)) {
            req.getSession().setAttribute("utilisateur", email);
        }

        resp.sendRedirect("connexion");
    }
}
