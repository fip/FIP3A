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
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.Convocation;
import metier.Dossier;
import rendering.HtmlContent;
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
@WebServlet(name = "ListerDossiers", urlPatterns = {"/ListerDossiers"})
public class ListerDossiers extends HttpServlet {

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
            out.println(html.header("Liste des dossiers"));

        Recrutement r = new Recrutement();
          
        
        //Change l'état du dossier si action && id existent.
        if(request.getParameter("action") != null && request.getParameter("id") != null)
        {
            if(request.getParameter("action").equals("qualifier"))
            {
                try
                {
                    r.qualifier(r.afficherDossier(Long.parseLong(request.getParameter("id"))));
                }   catch (Exception e) {out.println(html.error(e.getMessage()));}
            }
            else if (request.getParameter("action").equals("refuser"))
            {
                try
                {
                    r.refuser(r.afficherDossier(Long.parseLong(request.getParameter("id"))));
                }   catch (Exception e) {out.println(html.error(e.getMessage()));}
            }
            else if (request.getParameter("action").equals("valider"))
            {
                try
                {
                    r.valider(r.afficherDossier(Long.parseLong(request.getParameter("id"))));
                }   catch (Exception e) {out.println(html.error(e.getMessage()));}
           
            }
        }
        
        listerDossiers(r,out);
        out.println(html.footer());
            
        } finally {            
            out.close();
        }
    }

        private void listerDossiers(Recrutement r , PrintWriter out)
    {
        Collection<Dossier> dossiers = r.listerDossiers();
            
        if(!dossiers.isEmpty())
        {
            out.println("<br/><table style=\"border: 1px solid white;border-collapse: collapse;margin: auto;\">");
            
            out.println("<thead><tr style=\"text-align:center;\"><th>N° Dossier</th><th>Etat</th><th>Nom</th><th>Prénom</th><th>Titre de l'offre</th><th>Mission de l'offre</th><th>Salaire</th><th>Avis du recruteur</th><th>Qualifier</th><th>Convoquer</th><th>Etat Convocation</th><th>Valider</th><th>Refuser</th></tr></thead>");
               
            for (Dossier doss : dossiers) 
            {
                out.print("<tr style=\"text-align:center;\">");
                out.print("<td style=\"border: 1px solid white;padding:0.25em;padding: 5px;\">");
                out.print(doss.getId());
                out.println("</td>");
                out.print("<td style=\"border: 1px solid white;padding:0.25em;padding: 5px;\">");
                out.print(doss.getEtat());
                out.println("</td>");
                out.print("<td style=\"border: 1px solid white;padding:0.25em;padding: 5px;\">");
                out.print(doss.getCandidat().getNom());
                out.println("</td>");
                out.print("<td style=\"border: 1px solid white;padding:0.25em;padding: 5px;\">");
                out.print(doss.getCandidat().getPrenom());
                out.println("</td>");
                out.print("<td style=\"border: 1px solid white;padding:0.25em;padding: 5px;\">");
                out.print(doss.getOffre().getTitre());
                out.println("</td>");
                out.print("<td style=\"border: 1px solid white;padding:0.25em;\">");
                out.print(doss.getOffre().getMission());
                out.println("</td>");
                out.print("<td style=\"border: 1px solid white;padding:0.25em;\">");
                out.print(doss.getOffre().getSalaire());
                out.println("</td>");
                out.print("<td style=\"border: 1px solid white;padding:0.25em;\">");
                out.print(doss.getAvisRecruteur());
                out.println("</td>");
                out.print("<td style=\"border: 1px solid white;padding:0.25em;\"><a href=\"/GestionRH-war/ListerDossiers?action=qualifier&id="+doss.getId()+"\"><img alt=\"qualifier"+doss.getId()+"\" width=\"75px\" src=\"images/emprunter.png\"/></a></td>");
               
              
                //out.print("<td style=\"border: 1px solid white;padding:0.25em;\">");
                if(doss.getCandidat().getConvocations().iterator().hasNext()){
                                    out.print("<td style=\"border: 1px solid white;padding:0.25em;\"><a href=\"/GestionRH-war/ConvoquerCandidat?type=entretien&id="+doss.getCandidat().getId()+"\"><img alt=\"convoquer"+doss.getCandidat().getId()+"\" width=\"75px\" src=\"images/contacts.png\"/></a></td>");
  
                } else {
                                    out.print("<td style=\"border: 1px solid white;padding:0.25em;\"><a href=\"/GestionRH-war/ConvoquerCandidat?id="+doss.getCandidat().getId()+"\"><img alt=\"convoquer"+doss.getCandidat().getId()+"\" width=\"75px\" src=\"images/contacts.png\"/></a></td>");
  
                }
              
                //out.println("</td>");
                
                out.print("<td style=\"border: 1px solid white;padding:0.25em;\">");
                if(doss.getCandidat().getConvocations().iterator().hasNext()){
                    Convocation c = doss.getCandidat().getConvocations().iterator().next();
                    
                    out.print(c.getType()+" : "+c.getValidation());
                } else {
                    out.print("Non convoqué");
                }
              
                out.println("</td>");
                
                out.print("<td style=\"border: 1px solid white;padding:0.25em;\"><a href=\"/GestionRH-war/ListerDossiers?action=valider&id="+doss.getId()+"\"><img alt=\"valider\" width=\"75px\" src=\"images/validCheckBox.png\"/></a></td>");
                out.print("<td style=\"border: 1px solid white;padding:0.25em;\"><a href=\"/GestionRH-war/ListerDossiers?action=refuser&id="+doss.getId()+"\"><img width=\"75px\" src=\"images/crossCheckBox.png\"/></a></td>");
                out.println("</tr>");
            }
        } else {HtmlContent html = new HtmlContent(); out.println(html.error("Aucun dossier disponible"));};
            
             out.println("</table>");
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
            throws ServletException, IOException 
    {     
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
