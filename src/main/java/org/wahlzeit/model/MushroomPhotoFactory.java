package org.wahlzeit.model;

/**
 * Created by andreas on 07.11.16.
 */
public class MushroomPhotoFactory extends PhotoFactory
{
    static
    {
        setInstance(new MushroomPhotoFactory());//cannot happen twice - static blocks are run excactly once when class is loaded
    }

    protected MushroomPhotoFactory()
    {
        //do nothing
    }


    /**
     * @methodtype factory
     */
    @Override
    public Photo createPhoto()
    {
        return new MushroomPhoto();
    }


    /**
     * Creates a new photo with the specified id
     */
    @Override
    public Photo createPhoto(PhotoId id) {
        return new MushroomPhoto(id);
    }

    public Photo createPhoto(PhotoId id, Mushroom mushroom)
    {
        return new MushroomPhoto(id, mushroom);
    }

    /**
     * Loads a photo. The Java object is loaded from the Google Datastore, the Images in all sizes are loaded from the
     * Google Cloud storage.
     */
    @Override
    public Photo loadPhoto(PhotoId id) {
	   /* Photo result =
                OfyService.ofy().load().type(MushroomPhoto.class).ancestor(KeyFactory.createKey("Application", "Wahlzeit")).filter(Photo.ID, id).first().now();
        for (PhotoSize size : PhotoSize.values()) {
            GcsFilename gcsFilename = new GcsFilename("picturebucket", filename);



        }*/
        return null;
    }


}
