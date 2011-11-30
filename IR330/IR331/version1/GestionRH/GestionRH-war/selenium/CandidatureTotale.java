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

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestBase;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * 
 * @version 1.0 
 * @author Yohann lepage
 * @author Alexandre Besnard
 * @author Jonathan Morfin
 * @author Quentin Rousseau
 */
public class CandidatureTotale extends SeleneseTestBase {

    @Before
    public void beforeClass() {
        System.out.println("debut du test - before");
        selenium = new DefaultSelenium("localhost", 4444, "*googlechrome", "http://localhost:8080/");
        selenium.start();
        //selenium.setSpeed("1000");
        selenium.open("/GestionRH-war");
        selenium.waitForPageToLoad("30000");
    }

    @Test
    public void initialisationBDD() throws Exception {


        System.out.println("Début tests");
        System.out.println("Initialisation de la base de données");
        selenium.waitForPageToLoad("30000");
        selenium.click("link=Initialiser la Base de données");
        selenium.waitForPageToLoad("30000");
        assertTrue(selenium.isTextPresent("leTitre0"));
        System.out.println("Base de données initialisée");

        selenium.click("xpath=//img[@alt='RHBoard']");
        selenium.waitForPageToLoad("3000");

        System.out.println("Candidater à une offre");
        selenium.open("/GestionRH-war");
        selenium.waitForPageToLoad("30000");
        selenium.click("link=Postuler à une offre");
        selenium.waitForPageToLoad("30000");
        selenium.click("xpath=//img[@alt='2']");
        selenium.waitForPageToLoad("30000");
        selenium.type("prenom", "Le Prénom");
        selenium.type("nom", "Le Nom");
        selenium.click("xpath=//input[@type='submit']");
        selenium.waitForPageToLoad("30000");
        assertTrue(selenium.isTextPresent("Votre candidature a été prise en compte"));
        System.out.println("Candidature à une offre OK");

        
        System.out.println("Qualifier Dossier");
        selenium.click("xpath=//img[@alt='RHBoard']");
        selenium.waitForPageToLoad("3000");
        selenium.waitForPageToLoad("3000");
        selenium.click("link=Accéder aux dossiers de candidature pour en qualifier une");
        selenium.waitForPageToLoad("3000");
        selenium.click("xpath=//img[@alt='qualifier2']");
        selenium.waitForPageToLoad("3000");
        assertTrue(selenium.isTextPresent("qualifié"));
        System.out.println("Dossier qualifié");


        System.out.println("Envoyer convocation entretien psycho");
        selenium.click("xpath=//img[@alt='RHBoard']");
        selenium.waitForPageToLoad("3000");
        selenium.click("link=Accéder aux dossiers de candidature pour en qualifier une");
        selenium.waitForPageToLoad("3000");
        selenium.click("xpath=//img[@alt='convoquer2']");
        selenium.waitForPageToLoad("3000");
        selenium.type("date", "01/01/2011");
        selenium.click("xpath=//input[@type='submit']");
        selenium.waitForPageToLoad("3000");
        assertTrue(selenium.isTextPresent("Sat Jan 01 00:00:00 CET 2011"));
        selenium.click("link=Envoyer la convocation Psycho");
        selenium.waitForPageToLoad("3000");
        assertTrue(selenium.isTextPresent("Convo"));
        System.out.println("Convocation envoyée psycho OK");


        System.out.println("Accepter la convocation psycho");
        selenium.click("xpath=//img[@alt='RHBoard']");
        selenium.waitForPageToLoad("3000");
        selenium.click("link=Consulter ses convocations");
        selenium.waitForPageToLoad("3000");
        selenium.click("link=Accepter");
        selenium.waitForPageToLoad("3000");
        assertTrue(selenium.isTextPresent("Vous avez accepté la convocation. Attendez.."));
        selenium.click("xpath=//img[@alt='RHBoard']");
        selenium.waitForPageToLoad("3000");
        selenium.click("link=Accéder aux dossiers de candidature pour en qualifier une");
        selenium.waitForPageToLoad("3000");
        assertTrue(selenium.isTextPresent("psycho : true"));
        System.out.println("Convocation Psycho accepté OK");


        System.out.println("Envoyer convocation entretien");
        selenium.click("xpath=//img[@alt='RHBoard']");
        selenium.waitForPageToLoad("3000");
        selenium.waitForPageToLoad("3000");
        selenium.click("link=Accéder aux dossiers de candidature pour en qualifier une");
        selenium.waitForPageToLoad("3000");
        selenium.click("xpath=//img[@alt='convoquer2']");
        selenium.waitForPageToLoad("3000");
        selenium.type("date", "01/02/2011");
        selenium.click("xpath=//input[@type='submit']");
        selenium.waitForPageToLoad("3000");
        assertTrue(selenium.isTextPresent("2011-02-01"));
        selenium.click("link=Envoyer la convocation Entretien");
        selenium.waitForPageToLoad("3000");
        assertTrue(selenium.isTextPresent("Convocation Entretien envoyée"));
        System.out.println("Convocation Entretien envoyé");


        System.out.println("Accepter convocatio entretien");
        selenium.click("xpath=//img[@alt='RHBoard']");
        selenium.waitForPageToLoad("3000");
        selenium.click("link=Consulter ses convocations");
        selenium.waitForPageToLoad("3000");
        selenium.click("link=Accepter");
        selenium.waitForPageToLoad("3000");
        assertTrue(selenium.isTextPresent("Vous avez accepté la convocation. Attendez.."));
        selenium.click("xpath=//img[@alt='RHBoard']");
        selenium.waitForPageToLoad("3000");
        selenium.click("link=Accéder aux dossiers de candidature pour en qualifier une");
        selenium.waitForPageToLoad("3000");
        assertTrue(selenium.isTextPresent("entretien : true"));
        System.out.println("Convocation entretien accepté OK");


        System.out.println("Valider un dossier");
        selenium.click("xpath=//img[@alt='RHBoard']");
        selenium.waitForPageToLoad("3000");
        selenium.click("link=Accéder aux dossiers de candidature pour en qualifier une");
        selenium.waitForPageToLoad("3000");
        selenium.click("xpath=//img[@alt='valider']");
        selenium.waitForPageToLoad("3000");
        assertTrue(selenium.isTextPresent("validé"));
        System.out.println("Dossier validé");
        
        selenium.close();
        selenium.stop();
    }
}
