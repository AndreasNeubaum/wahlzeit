package org.wahlzeit.model;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.model.persistence.AbstractAdapterTest;
import org.wahlzeit.model.persistence.DatastoreAdapterTest;
import org.wahlzeit.model.persistence.GcsAdapterTest;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
            AbstractAdapterTest.class,
            DatastoreAdapterTest.class,
            GcsAdapterTest.class,
            AccessRightsTest.class,
            SphericCoordinateTest.class,
                CartesianCoordinateTest.class,
            FlagReasonTest.class,
            GenderTest.class,
            GuestTest.class,
            LocationTest.class,
            PhotoFilterTest.class,
            TagsTest.class,
            UserStatusTest.class,
            ValueTest.class,
                MushroomPhotoTest.class,
                MushroomPhotoFactoryTest.class
        })

public class ModelTestSuite
{
    //nothing to do here
}
