package JobsNow.servlets;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet("/mailgun")
public class MailGunServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String subject = req.getParameter("subject");
        String email = req.getParameter("email");
        String telephone = req.getParameter("telephone");
        String prenom = req.getParameter("prenom");
        String nom = req.getParameter("nom");

        SendSimpleMessage(subject, email, telephone, prenom, nom);
        resp.sendRedirect("Annonces");
    }

    public static ClientResponse SendSimpleMessage(String subject, String email, String telephone, String prenom, String nom) {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", "key-d1fdc746c3d6ebd64bdaa9aad8870941"));
        WebResource webResource = client.resource("https://api.mailgun.net/v3/sandbox56f7b4fd82484d9391dc30625de6a34e.mailgun.org/messages");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "Mailgun User <mailgun@sandbox56f7b4fd82484d9391dc30625de6a34e.mailgun.org>");
        formData.add("to", "maxence.mezin@hei.yncrea.fr");
        formData.add("subject", "Référence de l'annonce " + subject);
        formData.add("text", "Nom : " + nom + "\nPrénom : " + prenom + "\nTéléphone : " + telephone + "\nEmail : " + email);
        return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
                post(ClientResponse.class, formData);
    }

}
