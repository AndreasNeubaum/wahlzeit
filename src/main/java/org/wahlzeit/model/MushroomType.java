package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.services.DataObject;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


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
public class MushroomType extends DataObject
{
    /*@Ignore*/ private String name;
    /*@Ignore*/ private MushroomType superType;
    /*@Ignore*/ private Set<MushroomType> subtypes = new HashSet<MushroomType>();

    public MushroomType(String name)
    {
        this.name = name;
        this.superType = null;
    }

    public MushroomType(String name, MushroomType superType)
    {
        this.name = name;
        this.superType = superType;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public MushroomType getSuperType()
    {
        return superType;
    }

    public void setSuperType(MushroomType superType)
    {
        this.superType = superType;
    }

    public Iterator<MushroomType> getSubTypesIterator()
    {
        return subtypes.iterator();
    }

    public void addSubType(MushroomType subType)
    {
        assert subType != null : "tried to add null subtype";
        subType.superType = this;
        subtypes.add(subType);
    }

    public boolean hasInstance(Mushroom mushroom)
    {
        assert mushroom != null : "cannot have null as instance";

        if(mushroom.getMushroomType() == this)
            return true;

        return isSubtype(mushroom.getMushroomType());
    }

    public boolean isSubtype(MushroomType mushroomType)
    {
        assert mushroomType != null : "cannot be a subtype of null...";

        for(MushroomType subtype : subtypes)
        {
            if(subtype == mushroomType)
                return true;
        }

        return false;
    }

    public Mushroom createInstance()
    {
        return new Mushroom(this);
    }

    @Override
    public String toString()
    {
        return name;
    }
}
