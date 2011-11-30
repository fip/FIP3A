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
package candidater;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.Dossier;
import metier.core.Candidat;
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
@WebServlet(name = "Candidater", urlPatterns = {"/Candidater"})
public class Candidater extends HttpServlet {

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
            out.println(html.header("Votre candidature"));

            //Création du candidat
            if (!request.getParameter("prenom").isEmpty() || !request.getParameter("nom").isEmpty()) {
                //GesCandidat Gc = new GesCandidat();
                //Candidat candidat = Gc.creerCandidat(request.getParameter("prenom"), request.getParameter("nom"));

                Candidature c = new Candidature();
                Candidat candidat = c.creerCandidat(request.getParameter("prenom"), request.getParameter("nom"));
               
                //On rechercher l'offre désirée via son iD
              //  GesOffre Go = new GesOffre();
                Offre offre = c.afficherOffre(Long.parseLong(request.getParameter("id")));
                //On constitue le dossier
                //Dossier d = new Dossier("Created", candidat, offre);
                Dossier d = c.creerDossier(candidat, offre);

                //Message succès
                if (d != null) {
                    out.println(html.success("Votre candidature a été prise en compte. Nous vous recontacterons trés prochainement."));
                } else {
                    out.println(html.error("Une erreur s'est produite !"));
                }

            } else {
                out.println(html.error("Veuillez rentrer votre nom et votre prénom svp"));
            }

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
