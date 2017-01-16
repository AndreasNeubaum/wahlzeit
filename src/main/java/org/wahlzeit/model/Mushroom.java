package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Subclass;

import java.util.EnumSet;

/**
 * Created by andreas on 09.01.17.
 */

/*
* Einordnung in 6D Pattern-Raum
* 1) delegation of object creation: spereate object (MushroomManager)
* 2) sleection of concrete class: on the spot (es gibt nur eine concrete class --> hard coded)
* 3) configuraton of class mapping: in-code (weder anotationen noch config-file, au0erdem gibt es nur eine Klasse und daher nichts zu mappen)
* 4) instantiation of concrte class: in-code (MushroomManager ruft direkt new auf)
* 5) initialization of new pbject: by fixed signature (Konstruktor mit Parameterliste, keine hard-coded Konstanten)
* 6) N/A (Abhängige Objekte (MushroomType) werdne nicht selbst erzeugt, dependency injection)
*
* Instanziierung (beginnend beim MushroomManager):
* -MushroomManager.getInstance().createMushroom(String name, MushroomType MushroomType)
* --> dieser ruft new Mushroom() auf
* */

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
