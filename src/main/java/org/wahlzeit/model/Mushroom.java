package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Subclass;

import java.util.EnumSet;

/**
 * Created by andreas on 09.01.17.
 */

@Subclass
public class Mushroom
{
    @Ignore private MushroomType type;
    @Ignore private String name;
    @Ignore private boolean psychoactive;
    @Ignore private boolean medicalUse;
    @Ignore private EnumSet<Vorkommen> vorkommen;


    public Mushroom(MushroomType type)
    {
        this.type = type;
        this.vorkommen = EnumSet.noneOf(Vorkommen.class);
    }

    public Mushroom(String name, MushroomType type)
    {
        this.name = name;
        this.type = type;
        this.vorkommen = EnumSet.noneOf(Vorkommen.class);
    }

    public Mushroom(String name, boolean psychoactive, boolean medicalUse, EnumSet<Vorkommen> vorkommen, MushroomType type)
    {
        this.name = name;
        this.psychoactive = psychoactive;
        this.medicalUse = medicalUse;
        this.vorkommen = vorkommen == null ? EnumSet.noneOf(Vorkommen.class) : vorkommen;
        this.type = type;
    }

    public void setType(MushroomType type)
    {
        this.type = type;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPsychoactive(boolean psychoactive)
    {
        this.psychoactive = psychoactive;
    }

    public void setMedicalUse(boolean medicalUse)
    {
        this.medicalUse = medicalUse;
    }

    public void setVorkommen(EnumSet<Vorkommen> vorkommen)
    {
        this.vorkommen = vorkommen == null ? EnumSet.noneOf(Vorkommen.class) : vorkommen;
    }

    public String getName()
    {
        return name;
    }

    public boolean getPsychoactive()
    {
        return psychoactive;
    }

    public boolean getMedicalUse()
    {
        return medicalUse;
    }

    public EnumSet<Vorkommen> getVorkommen()
    {
        return vorkommen;
    }

    public MushroomType getMushroomType()
    {
        return type;
    }

    @Override
    public String toString()
    {
        return "Name: " + name + "Type: " + type + "Psychoactive: " + psychoactive + "MedicalUse: " + medicalUse + "Vorkommen: " + vorkommen;
    }
}
