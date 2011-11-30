<%@page import="rendering.HtmlContent"%>

<%
            HtmlContent html = new HtmlContent();
            out.println(html.header("Contacts"));
%>

<center>
    
    <img src="images/contacts.png" alt="Contacts" height="160"/><br/>
    
    <font size="3" color="white">
        Cette IHM a &eacute;t&eacute; r&eacute;alis&eacute; par <a href="mailto:quentin.rousseau@telecom-bretagne.eu">Rousseau Quentin</a>
        & <a href="mailto:alexandre.besnard@telecom-bretagne.eu">Besnard Alexandre</a>.<br/>
        <br/> Aujourd'hui les IHM font partie int&eacute;grante des applications. Elle constitue l'interface
        entre l'utilisateur et la machine.<br/><br/>
        Cette belle IHM n'est pas requise car on d&eacute;passe le cadre du module INF211 
        mais il est cependant &eacute;vident que donner du cachet &agrave; la vue externe d'une application est plus vendeur
        pour le client final.<br/><br/>
        Ceci est une d&eacute;monstration que l'on peut marier beaut&eacute; et fonctionnalit&eacute; au sein d'un m&ecirc;me projet.<br/><br/>

        <i>Quentin Rousseau</i> (Cr&eacute;ateur de <a href="http://thumbnailme.sourceforge.net/">Thumbnail me</a>)<br/><br/>
    </font>
</center>

<%
            out.println(html.footer());
%>