package org.wahlzeit.model;

import java.util.EnumSet;

import static java.util.EnumSet.*;

/**
 * Created by andreas on 07.11.16.
 */
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
    }

    public MushroomPhoto(PhotoId photoId)
    {
        super(photoId);
        psychoactive = true;
        medicalUse = false;
        geniessbarkeit = Geniessbarkeit.ToedlichGiftig;
        vorkommen = EnumSet.noneOf(Vorkommen.class);
    }

    public MushroomPhoto(PhotoId photoId, boolean psychoactive, boolean medicalUse, Geniessbarkeit geniessbarkeit, EnumSet<Vorkommen> vorkommen)
    {
        super(photoId);
        this.psychoactive = psychoactive;
        this.medicalUse = medicalUse;
        this.geniessbarkeit = geniessbarkeit;
        this.vorkommen = vorkommen;
    }

    public boolean getPsychoactive()
    {
        return psychoactive;
    }

    public void setPsychoactive(boolean psychoactive)
    {
        this.psychoactive = psychoactive;
    }

    public boolean getMedicalUse()
    {
        return medicalUse;
    }

    public void setMedicalUse(boolean medicalUse)
    {
        this.medicalUse = medicalUse;
    }

    public Geniessbarkeit getGeniessbarkeit()
    {
        return geniessbarkeit;
    }

    public void setGeniessbarkeit(Geniessbarkeit geniessbarkeit)
    {
        this.geniessbarkeit = geniessbarkeit;
    }

    public EnumSet<Vorkommen> getVorkommen()
    {
        return vorkommen;
    }

    public void setVorkommen(EnumSet<Vorkommen> vorkommen)
    {
        this.vorkommen = vorkommen;
    }
}
