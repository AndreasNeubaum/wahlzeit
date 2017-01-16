package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;


import java.util.EnumSet;

import static java.util.EnumSet.*;


/*
Einordnung in 6D Pattern-Raum
1) object creation: seperate object (MushroomPhotoFactory)
2) selection of concrete class: on the spot (2 verschiedene Factories zwar mit Vererbung aber ohne Delegation)
3) configuration of class mapping: In-code (hard-coded welche Photo-Klasse erzeugt wird)
4) instantiation of concrete class: in-code (MushroomPhotoFactory verwendet hard-coded new)
5) initialization: by-fixed-signature (Parameterliste im Konstruktir statt hard coded Konstanten)
6) building of object structure : N/A (Abhängige Objekte (Mushroom,MushroomType) werdne nicht selbst erzeugt, dependency injection)

Instanziierung (beginnend beim MushroomPhtotoManager):
-MushroomPhotoManager erbt alles vom PhotoManager und tauscht im static-Block die Instance-Variable aus
-Aufruf der vom PhotoManager geerbten Methode createPhoto(String filename, Image uploadedImage)
-PhotoUtil.createPhoto(filename, id, uploadedImage);
-PhotoFactory.getInstance().createPhoto(id)
-MushroomPhotoFactory tauscht in ihrem static-Block die Instance-Variable --> stattdessen wird MushroomPhotoFactory verwendet
-MushroomPhotoFactory überschreibt .createPhoto und ruft new MushroomPhoto() auf
* */

@Subclass
public class MushroomPhoto extends Photo
{
    public Mushroom getMushroom()
    {
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
