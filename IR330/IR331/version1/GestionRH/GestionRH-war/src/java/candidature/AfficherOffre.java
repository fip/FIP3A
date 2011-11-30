/*
 * GestionRH 
 *
 * Copyright (C) Maisel Team
 * 
 * GestionRH is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation; either version 2 of the License,
 * or (at your option) any later version.
 *
 * GestionRH  is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Cobertura; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */
package candidature;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.GesOffre;
import metier.core.Offre;
import rendering.HtmlContent;
import systeme.Candidature;

/**
 * 
 * 
 * @version 1.0 
 * @author Yohann lepage
 * @author Alexandre Besnard
 * @author Jonathan Morfin
 * @author Quentin Rousseau
 */
@WebServlet(name = "AfficherOffre", urlPatterns = {"/AfficherOffre"})
public class AfficherOffre extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {

            HtmlContent html = new HtmlContent();
            out.println(html.header("Détail de l'offre"));
            

            Candidature c = new Candidature();
            Offre o = c.afficherOffre(Long.parseLong(request.getParameter("id")));

                      
            if (o != null)
            {
            out.println("<br/><table style=\"border: 1px solid white;border-collapse: collapse;margin: auto;\">");
            
            out.println("<thead><tr style=\"text-align:center;\"><th>Titre</th><th>Mission</th><th>Salaire</th></tr></thead>");
            
                out.print("<tr style=\"text-align:center;\">");
                out.print("<td style=\"border: 1px solid white;padding:0.25em;\">");
                out.print(o.getTitre());
                out.println("</td>");
                out.print("<td style=\"border: 1px solid white;padding:0.25em;\">");
                out.print(o.getMission());
                out.println("</td>");
                out.print("<td style=\"border: 1px solid white;padding:0.25em;\">");
                out.print(o.getSalaire());
                out.println("</td></tr>");
                          
            out.println("</table><br/>");
            
            //Formulaire
            out.println("<table style=\"border-collapse: collapse;margin: auto;\">");
            out.println("<form action=\"/GestionRH-war/Candidater?id=" + request.getParameter("id") + "\" method=\"post\">");
            out.println("<tr><td><p style=\"color:red;\">Postuler dès maintenant !</p></td></tr>");
            out.println("<tr><td><label>Pr&eacute;nom : </td><td><input type=\"text\" name=\"prenom\"/></label></td><tr>");
            out.println("<tr><td><label>Nom : </td><td> <input type=\"text\" name=\"nom\"/></label></td><tr>");
            out.println("<tr><td><label>Curriculum vitae : </td><td><input type=\"file\" name=\"cv\"/></label></td><tr>");
            out.println("<tr><td><label>Lettre de motivation : </td><td> <input type=\"file\" name=\"ldm\"/></label></td><tr>");
            out.println("<tr><td><input type=\"submit\" nom=\"valider\" value=\"Envoyer\"/></td><tr>");
            out.println("</form>");
            out.println("</table>");
            
            } else out.println(html.error("Cette offre n'existe pas"));
            
            
            out.println(html.footer());
            
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
