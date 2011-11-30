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
package gesRH;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.GesCandidat;
import metier.GesEmploye;
import metier.GesOffre;
import metier.core.Candidat;
import metier.core.Employe;
import metier.core.Offre;
import rendering.HtmlContent;
import systeme.GestionRH;

/**
 * 
 * 
 * @version 1.0 
 * @author Yohann lepage
 * @author Alexandre Besnard
 * @author Jonathan Morfin
 * @author Quentin Rousseau
 */
@WebServlet(name = "PeuplerBase", urlPatterns = {"/PeuplerBase"})
public class PeuplerBase extends HttpServlet {

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
            out.println(html.header("IR331 : Bienvenue dans l'espace RH"));

            //EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionRH-ejbPU");
            //EntityManager em = emf.createEntityManager();

           /* GestionRH ges = new GestionRH(em);
            GesCandidat c = new GesCandidat(em);
            GesEmploye e = new GesEmploye(em);
            GesOffre o = new GesOffre(em);*/
            
             GestionRH ges = new GestionRH();
            GesCandidat c = new GesCandidat();
            GesEmploye e = new GesEmploye();
            GesOffre o = new GesOffre();
            Boolean peuplerBase = ges.peuplerBase(10);
                    
            Collection<Employe> employes = e.listerEmployes();



            Collection<Offre> offres = o.listerOffres();
            out.println("<h2>Offres</h2><code>");
            for (Offre off : offres) // pour chaque String current dans a
            {

                out.println(off.toString() + "</br>");
            }
            out.println("</code>");



            out.println("<h2>Employes</h2><code>");
            for (Employe empl : employes) // pour chaque String current dans a
            {

                out.println(empl.toString() + "</br>");
            }
            out.println("</code>");


            Collection<Candidat> candidats = c.listerCandidats();
            out.println("<h2>Candidats</h2><code>");
            for (Candidat cand : candidats) // pour chaque String current dans a
            {

                out.println(cand.toString() + "</br>");
            }
            out.println("</code>");

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
