package JobsNow.servlets;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet("/contacter")
public class MailGunContactServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ClientResponse clientResponse;
        String email = req.getParameter("email");
        String telephone = req.getParameter("telephone");
        String prenom = req.getParameter("prenom");
        String nom = req.getParameter("nom");
        String statut = req.getParameter("statut");
        String message = req.getParameter("message");
        clientResponse = SendSimpleMessage(email, telephone, prenom, nom, statut, message);
        resp.sendRedirect("JobsNow");
    }

    public ClientResponse SendSimpleMessage(String email, String telephone, String prenom, String nom, String statut, String message) {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", "key-d1fdc746c3d6ebd64bdaa9aad8870941"));
        WebResource webResource = client.resource("https://api.mailgun.net/v3/sandbox56f7b4fd82484d9391dc30625de6a34e.mailgun.org/messages");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "Mailgun User <mailgun@sandbox56f7b4fd82484d9391dc30625de6a34e.mailgun.org>");
        formData.add("to", "maxence.mezin@hei.yncrea.fr");
        formData.add("subject", "Prise de contact ");
        formData.add("text", "Nom : "+nom+"\nPrénom : "+prenom+"\nTéléphone : "+telephone+"\nEmail : "+email+"\nStatut : "+statut+"\nMessage : "+message);
        return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
    }
}
