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
import static org.junit.Assert.assertNull;


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
        assertNull(mp.getMushroom());
    }

    @Test
    public void TestFullConstructor()
    {
        MushroomType mt = new MushroomType("t1");
        MushroomPhoto mp = new MushroomPhoto(PhotoId.getNextId(), new Mushroom("m1", true, true, EnumSet.noneOf(Vorkommen.class), mt));
        assertTrue(mp.getMushroom().getPsychoactive());
        assertTrue(mp.getMushroom().getMedicalUse());
        assertEquals("m1", mp.getMushroom().getName());
        assertFalse(mp.getMushroom().getVorkommen().isEmpty());
        assertEquals(mt, mp.getMushroom().getMushroomType());
    }

    @Test
    public void TestGetSetPsychoactive()
    {
        MushroomPhoto mp = new MushroomPhoto(PhotoId.getNextId(), new Mushroom("name", new MushroomType("type")));
        assertFalse(mp.getMushroom().getPsychoactive());
        mp.getMushroom().setPsychoactive(true);
        assertTrue(mp.getMushroom().getPsychoactive());
    }

    @Test
    public void TestGetSetMedicalUse()
    {
        MushroomPhoto mp = new MushroomPhoto(PhotoId.getNextId(), new Mushroom("name", new MushroomType("type")));
        assertFalse(mp.getMushroom().getMedicalUse());
        mp.getMushroom().setMedicalUse(true);
        assertTrue(mp.getMushroom().getMedicalUse());
    }

    @Test
    public void TestGetSetVorkommen()
    {
        MushroomPhoto mp = new MushroomPhoto(PhotoId.getNextId(), new Mushroom("name", new MushroomType("type")));
        assertTrue(mp.getMushroom().getVorkommen().isEmpty());
        mp.getMushroom().setVorkommen(EnumSet.allOf(Vorkommen.class));
        assertFalse(mp.getMushroom().getVorkommen().isEmpty());
    }
}
