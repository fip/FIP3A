<%@page import="rendering.HtmlContent"%>

<%
        
            HtmlContent html = new HtmlContent();
            out.println(html.header("IR331 : Bienvenue dans l'espace RH"));
%>
            <center><img src="images/abonne.png" alt="GestionRH" height="160" /></center>
            <ul>
                <li><h2 style="color:darksalmon;">D&eacute;marrage...</h2></li>
                <li><a href="/GestionRH-war/PeuplerBase">Initialiser la Base de données</a></li>
                <li><h2 style="color:darksalmon;">Candidater</h2></li>
                <li style="color:white;">Candidat : <a href="/GestionRH-war/ListerOffres">Postuler à une offre</a></li>
                <li style="color:white;">Candidat : <a href="/GestionRH-war/GererConvocations">Consulter ses convocations</a></li>
                <li><h2 style="color:darksalmon;">Candidater</h2></li>
                <li style="color:white;">Charg&eacute; de recrutement : <a href="/GestionRH-war/ListerDossiers">Accéder aux dossiers de candidature pour en qualifier une</a></li>
            </ul>
<%
            out.println(html.footer());
%>