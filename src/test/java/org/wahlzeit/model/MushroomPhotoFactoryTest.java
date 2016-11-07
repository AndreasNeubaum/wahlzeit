package org.wahlzeit.model;

/**
 * Created by andreas on 07.11.16.
 */

import com.google.appengine.tools.development.testing.LocalBlobstoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MushroomPhotoFactoryTest
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
    public void TestCreatePhotoType() throws ClassNotFoundException
    {
        Class.forName("org.wahlzeit.model.MushroomPhotoFactory");//load class manually for test, in real environment loaded automatically
        assertTrue(MushroomPhotoFactory.getInstance().createPhoto() instanceof MushroomPhoto);
    }
}
