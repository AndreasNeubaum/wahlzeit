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
    private boolean psychoactive;
    private boolean medicalUse;
    private Geniessbarkeit geniessbarkeit;
    private EnumSet<Vorkommen> vorkommen;

    public MushroomPhoto()
    {
        super();
        psychoactive = true;
        medicalUse = false;
        geniessbarkeit = Geniessbarkeit.ToedlichGiftig;
        vorkommen = EnumSet.noneOf(Vorkommen.class);

        assertClassInvariants();
    }

    public MushroomPhoto(PhotoId photoId)
    {
        super(photoId);
        psychoactive = true;
        medicalUse = false;
        geniessbarkeit = Geniessbarkeit.ToedlichGiftig;
        vorkommen = EnumSet.noneOf(Vorkommen.class);

        assertClassInvariants();
    }

    public MushroomPhoto(PhotoId photoId, boolean psychoactive, boolean medicalUse, Geniessbarkeit geniessbarkeit, EnumSet<Vorkommen> vorkommen)
    {
        super(photoId);
        this.psychoactive = psychoactive;
        this.medicalUse = medicalUse;
        this.geniessbarkeit = geniessbarkeit;
        this.vorkommen = vorkommen;

        assertClassInvariants();
    }

    public boolean getPsychoactive()
    {
        return psychoactive;
    }

    public void setPsychoactive(boolean psychoactive)
    {
        assertClassInvariants();
        this.psychoactive = psychoactive;
        assertClassInvariants();
    }

    public boolean getMedicalUse()
    {
        return medicalUse;
    }

    public void setMedicalUse(boolean medicalUse)
    {
        assertClassInvariants();
        this.medicalUse = medicalUse;
        assertClassInvariants();
    }

    public Geniessbarkeit getGeniessbarkeit()
    {
        return geniessbarkeit;
    }

    public void setGeniessbarkeit(Geniessbarkeit geniessbarkeit)
    {
        assertClassInvariants();
        this.geniessbarkeit = geniessbarkeit;
        assertClassInvariants();
    }

    public EnumSet<Vorkommen> getVorkommen()
    {
        return vorkommen;
    }

    public void setVorkommen(EnumSet<Vorkommen> vorkommen)
    {
        assertClassInvariants();
        this.vorkommen = vorkommen;
        assertClassInvariants();
    }
}
