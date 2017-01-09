package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;


import java.util.EnumSet;

import static java.util.EnumSet.*;

/**
 * Created by andreas on 07.11.16.
 */
@Subclass
public class MushroomPhoto extends Photo
{
    public Mushroom getMushroom() {
        return mushroom;
    }

    private Mushroom mushroom;

    public MushroomPhoto()
    {
        super();
        mushroom = null;

        assertClassInvariants();
    }

    public MushroomPhoto(PhotoId photoId)
    {
        super(photoId);
        mushroom = null;

        assertClassInvariants();
    }

    public MushroomPhoto(PhotoId photoId, Mushroom mushroom)
    {
        super(photoId);
        this.mushroom = mushroom;

        assertClassInvariants();
    }

    public void setMushroom(Mushroom mushroom)
    {
        this.mushroom = mushroom;
    }
}
