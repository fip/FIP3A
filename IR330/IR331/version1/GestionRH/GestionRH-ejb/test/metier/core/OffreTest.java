/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier.core;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests
 * 
 * @version 1.0 
 * @author Yohann lepage
 * @author Alexandre Besnard
 * @author Jonathan Morfin
 * @author Quentin Rousseau
 */
public class OffreTest {
    
    public OffreTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Offre.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Offre instance = new Offre();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Offre.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        Offre instance = new Offre();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Offre.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Offre instance = new Offre();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Offre.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Offre instance = new Offre();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Offre.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Offre instance = new Offre();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMission method, of class Offre.
     */
    @Test
    public void testGetMission() {
        System.out.println("getMission");
        Offre instance = new Offre();
        String expResult = "";
        String result = instance.getMission();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSalaire method, of class Offre.
     */
    @Test
    public void testGetSalaire() {
        System.out.println("getSalaire");
        Offre instance = new Offre();
        Integer expResult = null;
        Integer result = instance.getSalaire();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTitre method, of class Offre.
     */
    @Test
    public void testGetTitre() {
        System.out.println("getTitre");
        Offre instance = new Offre();
        String expResult = "";
        String result = instance.getTitre();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
