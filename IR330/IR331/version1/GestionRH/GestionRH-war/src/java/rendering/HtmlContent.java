package rendering;

import org.apache.commons.lang.StringEscapeUtils;

/*******************************************************************************
 *	Bibliothèque - IHM
 *
 *	Bibliothèque de médias
 *
 *	@package	Bibliotheque.html
 *	@author		Alexandre Besnard, Quentin Rousseau
 *	@copyright	Quentin Rousseau, Alexandre Besnard (c) 2010-2011
 *
 *	@version	1.0
 *
 *	@filesource	HtmlContent.java
 *      @role	 	Cette classe permet principalement de générer le header
 *                      et le footer d'une page HTML.
 *      
 *******************************************************************************/

public class HtmlContent {

    public HtmlContent() {
    }

    public String header(String title) {
        return "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"
                + "<html>"
                + "<head>"
                + "<meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\" />"
                + "<title>" + title + "</title>"
                + "<link type=\"text/css\" href=\"menu.css\" rel=\"stylesheet\" />"
                + "<script type=\"text/javascript\" src=\"jquery.js\"></script>"
                + "<script type=\"text/javascript\" src=\"menu.js\"></script>"
                + "</head>"
                + "<style type=\"text/css\">* { margin:0;padding:0;} body { background:rgb(74,81,85); }"
                + "div#menu { margin:5px auto; }"
                + "div#copyright {font:11px 'Trebuchet MS';color:#fff;text-indent:30px;padding:40px 0 0 0;}"
                + "div#copyright a { color:#9acd32; }"
                + "div#copyright a:hover { color:#fff; }"
                + "</style>"
                + "<center><a href=\"/GestionRH-war\"><img style=\"border:none;\" src=\"images/rhBoard.png\" alt=\"RHBoard\" height=\"160\" /></center></a>"
                + "<div id=\"menu\">"
                + "<ul class=\"menu\">"
                + "<li><a href=\"#\" class=\"parent\"><span>" + StringEscapeUtils.escapeHtml("Candidater") + "</span></a>"
                + "<ul>"
                + "<li><a href=\"/GestionRH-war/ListerOffres\"><span>Lister les offres</span></a></li>"
                + "<li><a href=\"/GestionRH-war/LireMonDossier\"><span>Lister mon dossier</span></a></li>"
                + "<li><a href=\"/GestionRH-war/GererConvocations\"><span>Consulter mes convocations</span></a></li>"
                + "</ul>"
                + "</li>"
                + "<li><span><a href=\"#\">" + StringEscapeUtils.escapeHtml("Recruter") + "</a></span>"
                + "<ul>"
                + "<li><a href=\"/GestionRH-war/ListerDossiers\"><span>Lister les dossiers</span></a></li>"
                + "</ul>"
                + "</li>"
                /*
                + "<ul>"
                + "<li><a href=\"#\" class=\"parent\"><span>Livre</span></a>"
                + "<ul>"
                + "<li><a href=\"/GestionRH-war/AjouterLivreFormServlet\"><span>Ajouter</span></a></li>"
                + "<li><a href=\"/GestionRH-war/ModifierLivreServlet\"><span>Modifier & Supprimer</span></a></li>"
                + "<li><a href=\"/GestionRH-war/EmprunterLivreServlet\"><span>Emprunter/Réserver</span></a></li>"
                + "</ul>"
                + "</li>"
                + "<li><a href=\"#\" class=\"parent\"><span>Dvd</span></a>"
                + "<ul>"
                + "<li><a href=\"/GestionRH-war/AjouterDVDFormServlet\"><span>Ajouter</span></a></li>"
                + "<li><a href=\"/GestionRH-war/ModifierDVDServlet\"><span>Modifier & Supprimer</span></a></li>"
                + "<li><a href=\"/GestionRH-war/EmprunterDVDServlet\"><span>Emprunter/Réserver</span></a></li>"
                + "</ul>"
                + "</li>"
                + "</ul>"
                + "</li>"
                + "<li><a href=\"#\"><span>Help</span></a></li>"
                + "<li class=\"last\"><a href=\"contacts.jsp\"><span>Contacts</span></a></li>"
                */
                + "</ul>"
                + "</div><body>"
                + "<center><h1>" + title + "</h1></center>";

    }

    public String footer() {

        return "</body>"
                + "<div id=\"copyright\">Copyright &copy; 2011-2012 | IR331 : Logiciel isolé par <a href=\"mailto:quentin.rousseau@telecom-bretagne.eu\">Rousseau Quentin</a>"
                + " & <a href=\"mailto:alexandre.besnard@telecom-bretagne.eu\">Besnard Alexandre</a>"
                + " & <a href=\"mailto:johnatan.morfin@telecom-bretagne.eu\">Morfin Jonathan</a>"
                + " & <a href=\"mailto:yohann.lepage@telecom-bretagne.eu\">Lepage Yohann</a> | Design par <a href=\"http://apycom.com/\">Apycom jQuery Menus</a></div>"
                + "<br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />"
                + "<br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />"
                + "</body>"
                + "</html>";

    }

    public String error(String error) {
        return "<center><div><table><tr><td><img src=\"images/info.png\" alt=\"Note\" height=\"160\" /></td><td><font color=\"red\">" + StringEscapeUtils.escapeHtml(error) + "</font></div></td></tr></table></center>";
    }

    public String success(String msg) {
        return "<center><div><table><tr><td><img src=\"images/validCheckBox.png\" alt=\"Note\" height=\"160\" /></td><td><font color=\"darksalmon\">" + StringEscapeUtils.escapeHtml(msg) + "</font></div></td></tr></table></center>";
    }
}
