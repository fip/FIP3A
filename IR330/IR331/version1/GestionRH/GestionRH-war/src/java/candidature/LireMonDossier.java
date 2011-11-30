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
import java.util.Collection;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.Dossier;
import metier.core.Candidat;
import rendering.HtmlContent;
import systeme.Candidature;
import systeme.Recrutement;

/**
 * 
 * 
 * @version 1.0 
 * @author Yohann lepage
 * @author Alexandre Besnard
 * @author Jonathan Morfin
 * @author Quentin Rousseau
 */
@WebServlet(name = "LireMonDossier", urlPatterns = {"/LireMonDossier"})
public class LireMonDossier extends HttpServlet {

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
            out.println(html.header("Votre dossier"));
            
            //Identification du candidat    
            out.println("<table style=\"border-collapse: collapse;margin: auto;\">");
            out.println("<form action=\"/GestionRH-war/LireMonDossier\" method=\"post\">");
            out.println("<tr><td><p style=\"color:red;\">Identifiez vous ! (Sensible à la casse)</p></td></tr>");
            out.println("<tr><td><label>Nom : </td><td> <input type=\"text\" name=\"nom\"/></label></td><tr>");
            out.println("<tr><td><label>Pr&eacute;nom : </td><td><input type=\"text\" name=\"prenom\"/></label></td><tr>");
            out.println("<tr><td><input type=\"submit\" nom=\"valider\" value=\"Envoyer\"/></td><tr>");
            out.println("</form>");
            out.println("</table>");
            
            if(request.getParameter("nom") != null && !request.getParameter("nom").isEmpty() && request.getParameter("prenom") !=  null && !request.getParameter("prenom").isEmpty())
            {
             Recrutement o = new Recrutement();
             try
             {
             Candidat c = o.afficherCandidat(request.getParameter("nom"),request.getParameter("prenom"));
             afficheDossier(out,o.afficherDossier(c.getId()));
             }
             catch (NoResultException e)  {out.println(html.error("Ce candidat n'existe pas !"));}
             catch (NonUniqueResultException e) {out.println(html.error("Plusieurs candidats portent ce nom ET ce prénom!"));}
             catch (Exception e)  {out.println(e.getMessage()+e.getClass());}
             
            }
            else out.println(html.error("Veuillez saisir votre nom et votre prénom !"));
                        
                       
            out.println(html.footer());

        } finally {
            out.close();
        }
    }

    public void afficheDossier(PrintWriter out, Dossier d)
    {
        if(d != null)
        {
                out.println("<br/><table  style=\"border: 1px solid white;border-collapse: collapse;margin: auto;\">");

                out.println("<thead><tr style=\"text-align:center;\"><th>ID</th><th>Etat</th><th>Titre de l'offre</th><th>Mission</th><th>Salaire</th></tr></thead>");

                    out.print("<tr style=\"text-align:center;\">");
                    out.print("<td style=\"border: 1px solid white;padding:0.25em;\">");
                    out.print(d.getId());
                    out.println("</td>");
                    out.print("<td style=\"border: 1px solid white;padding:0.25em;\">");
                    out.print(d.getEtat());
                    out.println("</td>");
                    out.print("<td style=\"border: 1px solid white;padding:0.25em;\">");
                    out.print(d.getOffre().getTitre());
                    out.println("</td>");
                    out.print("<td style=\"border: 1px solid white;padding:0.25em;\">");
                    out.print(d.getOffre().getMission());
                    out.println("</td>");
                    out.print("<td style=\"border: 1px solid white;padding:0.25em;\">");
                    out.print(d.getOffre().getSalaire());
                    out.println("</td>");
                    out.println("</tr>");
         
                 out.println("</table>");
        } else {HtmlContent html = new HtmlContent();out.println(html.error("Vous n'avez aucun dossier en cours !"));}
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