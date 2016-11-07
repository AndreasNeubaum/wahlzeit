package org.wahlzeit.model;

import com.google.appengine.tools.development.testing.LocalBlobstoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.EnumSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreas on 07.11.16.
 */
public class MushroomPhotoTest
{
    //required to instanciate a photot class - really needs to initialize all the google api for this :(
    private static final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalBlobstoreServiceTestConfig());

    @BeforeClass
    public static void beforeClass()
    {
        helper.setUp();
    }

    @AfterClass
    public static void afterClass()
    {
        helper.tearDown();
    }

    @Test
    public void TestDefaultConstructor()
    {
        MushroomPhoto mp = new MushroomPhoto();
        assertTrue(mp.getPsychoactive());
        assertFalse(mp.getMedicalUse());
        assertEquals(Geniessbarkeit.ToedlichGiftig, mp.getGeniessbarkeit());
        assertTrue(mp.getVorkommen().isEmpty());
    }

    @Test
    public void TestIdConstructor()
    {
        MushroomPhoto mp = new MushroomPhoto(PhotoId.getNextId());
        assertTrue(mp.getPsychoactive());
        assertFalse(mp.getMedicalUse());
        assertEquals(Geniessbarkeit.ToedlichGiftig, mp.getGeniessbarkeit());
        assertTrue(mp.getVorkommen().isEmpty());
    }

    @Test
    public void TestFullConstructor()
    {
        MushroomPhoto mp = new MushroomPhoto(PhotoId.getNextId(), false, true, Geniessbarkeit.Ungeniessbar, EnumSet.allOf(Vorkommen.class));
        assertFalse(mp.getPsychoactive());
        assertTrue(mp.getMedicalUse());
        assertEquals(Geniessbarkeit.Ungeniessbar, mp.getGeniessbarkeit());
        assertFalse(mp.getVorkommen().isEmpty());
    }

    @Test
    public void TestGetSetPsychoactive()
    {
        MushroomPhoto mp = new MushroomPhoto();
        assertTrue(mp.getPsychoactive());
        mp.setPsychoactive(false);
        assertFalse(mp.getPsychoactive());
    }

    @Test
    public void TestGetSetMedicalUse()
    {
        MushroomPhoto mp = new MushroomPhoto();
        assertFalse(mp.getMedicalUse());
        mp.setMedicalUse(true);
        assertTrue(mp.getMedicalUse());
    }

    @Test
    public void TestGetSetGeniessbarkeit()
    {
        MushroomPhoto mp = new MushroomPhoto();
        assertEquals(Geniessbarkeit.ToedlichGiftig, mp.getGeniessbarkeit());
        mp.setGeniessbarkeit(Geniessbarkeit.Ungeniessbar);
        assertEquals(Geniessbarkeit.Ungeniessbar, mp.getGeniessbarkeit());
    }

    @Test
    public void TestGetSetVorkommen()
    {
        MushroomPhoto mp = new MushroomPhoto();
        assertTrue(mp.getVorkommen().isEmpty());
        mp.setVorkommen(EnumSet.allOf(Vorkommen.class));
        assertFalse(mp.getVorkommen().isEmpty());
    }
}
