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
package recruter;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.Convocation;
import rendering.HtmlContent;
import systeme.Candidature;
import systeme.Convoquer;
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
@WebServlet(name = "ConvoquerCandidat", urlPatterns = {"/ConvoquerCandidat"})
public class ConvoquerCandidat extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        
        try {
            HtmlContent html = new HtmlContent();
            out.println(html.header("Création de la convocation"));

            //Création du la convocation
            if ((request.getParameter("date") == null) && (request.getParameter("confirmation") == null)&& (request.getParameter("type") == null)) 
            {
                out.print("<br/><center><form action=\"/GestionRH-war/ConvoquerCandidat?type=psycho&confirmation=KO&id=" + request.getParameter("id") + "\" method=\"post\">");
                out.print("<label>Date  format JJ/MM/AA: <input type=\"text\" name=\"date\"/></label>");
                out.print("<input type=\"submit\" nom=\"valider\" value=\"Envoyer\"/>");
                out.print("</form></center>");
            }
            else if ((request.getParameter("date") == null) && (request.getParameter("confirmation") == null)&& (request.getParameter("type").equals("entretien"))) 
            {
                Recrutement r = new Recrutement();
                
                out.print("<br/><center><form action=\"/GestionRH-war/ConvoquerCandidat?type=entretien&confirmation=KO&id=" + r.afficherCandidat(Long.parseLong(request.getParameter("id"))).getDossiers().iterator().next().getId() + "\" method=\"post\">");
                out.print("<label>Date  format JJ/MM/AA: <input type=\"text\" name=\"date\"/></label>");
                out.print("<input type=\"submit\" nom=\"valider\" value=\"Envoyer\"/>");
                out.print("</form></center>");
            }
            else if (request.getParameter("confirmation").equals("KO") && request.getParameter("type").equals("psycho")) 
            {
                Convoquer c = new Convoquer();
                Recrutement r = new Recrutement();
                SimpleDateFormat a = new SimpleDateFormat("dd/MM/yy");

                Convocation conv = c.creerConvocation(r.afficherCandidat(Long.parseLong(request.getParameter("id"))), a.parse(request.getParameter("date")));
                out.print("<ul>");
                out.print("<li>>Date : " + conv.getDatec() + "</li>");
                out.print("<li>><a href=\"/GestionRH-war/ConvoquerCandidat?type=psycho&confirmation=OK&id=" + request.getParameter("id") + "\"/>Envoyer la convocation Psycho</a></li>");
                out.print("<ul>");

            }
            else if (request.getParameter("confirmation").equals("OK")&& request.getParameter("type").equals("psycho")) 
            {
                out.print("Convocation Psycho envoy&eacute;e");
            }
            else if (request.getParameter("confirmation").equals("KO") && request.getParameter("type").equals("entretien")) 
            {
                Convoquer c = new Convoquer();
             
                SimpleDateFormat a = new SimpleDateFormat("dd/MM/yy");
                
                Convocation conv = c.creerConvocationEntretien(Long.parseLong(request.getParameter("id")), a.parse(request.getParameter("date")));
                Convocation co = c.listerConvocation().iterator().next();
                out.print("<ul>");
                out.print("<li>>Date : " + co.getDatec() + "</li>");
                out.print("<li>><a href=\"/GestionRH-war/ConvoquerCandidat?type=entretien&confirmation=OK&id=" + co.getId() + "\"/>Envoyer la convocation Entretien</a></li>");
                out.print("<ul>");
            }
            else if (request.getParameter("confirmation").equals("OK")&& request.getParameter("type").equals("entretien")) 
            {
                out.print("Convocation Entretien envoy&eacute;e");
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ConvoquerCandidat.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ConvoquerCandidat.class.getName()).log(Level.SEVERE, null, ex);
        }
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
