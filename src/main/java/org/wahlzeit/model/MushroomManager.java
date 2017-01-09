package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Ignore;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class MushroomManager 
{
    @Ignore private Map<String, Mushroom> Mushrooms = new HashMap<String, Mushroom>();
    @Ignore private Map<String, MushroomType> MushroomTypes = new HashMap<String, MushroomType>();

    private static MushroomManager instance;


    private MushroomManager()
    {
    }

    public static synchronized MushroomManager getInstance()
    {
        if (instance == null)
        {
            instance = new MushroomManager();
        }

        return instance;
    }


    public MushroomType getMushroomType(String MushroomTypeName)
    {
        return MushroomTypes.get(MushroomTypeName);
    }


    public MushroomType createMushroomType(String MushroomTypeName)
    {
        return addMushroomType(new MushroomType(MushroomTypeName));
    }


    public Mushroom createMushroom(String name, MushroomType MushroomType)
    {
        Mushroom result = new Mushroom(name, MushroomType);
        Mushrooms.put(result.toString(), result);

        return result;
    }

    public Mushroom createMushroom(String name, String MushroomTypeName)
    {
        MushroomType mt = getMushroomType(MushroomTypeName);
        Mushroom result = mt.createInstance();
        result.setName(name);
        Mushrooms.put(result.toString(), result);

        return result;
    }

    public Mushroom createMushroom(String MushroomTypeName)
    {
        MushroomType mt = getMushroomType(MushroomTypeName);
        Mushroom result = mt.createInstance();
        Mushrooms.put(result.toString(), result);
        return result;
    }

    public Mushroom createMushroom(String name, MushroomType MushroomType, boolean psychoactive, boolean medicalUse, EnumSet<Vorkommen> vorkommen)
    {
        Mushroom result = new Mushroom(name, psychoactive, medicalUse, vorkommen, MushroomType);
        Mushrooms.put(result.toString(), result);

        return result;
    }


    private MushroomType addMushroomType(MushroomType MushroomType)
    {
        if (!MushroomTypes.containsKey(MushroomType.getName())) {
            MushroomTypes.put(MushroomType.getName(), MushroomType);
        }

        return MushroomType;
    }
}