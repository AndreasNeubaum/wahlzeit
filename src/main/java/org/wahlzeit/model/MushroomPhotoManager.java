package org.wahlzeit.model;

/**
 * Created by andreas on 07.11.16.
 */
public class MushroomPhotoManager extends PhotoManager
{
    static
    {
        instance = new MushroomPhotoManager();
    }

    //nothing to override since phtotos are (de)serialized --> automatically includes new attributes
}
