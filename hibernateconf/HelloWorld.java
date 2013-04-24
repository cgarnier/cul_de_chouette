

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorld extends HttpServlet{

	//compteur généré par la servlet
	protected int compteur = 0;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		// out: flex de sortie mode texte qui contiendra le HTML
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			// on écrit ligne par ligne le code html à afficher chez le client
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet HelloWolrd</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet HelloWolrd</h1>");
			// on insère ici la valeur du compteur en l'incrémentant au passage
			out.println("<p>Nombre d'appels :"+(++compteur)+"</p>");
			out.println("</body>");
			out.println("</html>");
		}
		finally {
			out.close();
		}		
	}
}
