package JobsNow.servlets;

import JobsNow.entities.Annonce;
import JobsNow.services.AnnonceService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delAnnonce")
public class DelAnnonceServlet extends HttpServlet {

   @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Integer idAnnonce = Integer.parseInt(req.getParameter("idAnnonce"));
       AnnonceService.getInstance().delAnnonce(idAnnonce);

       resp.sendRedirect("Administration");
   }
}
