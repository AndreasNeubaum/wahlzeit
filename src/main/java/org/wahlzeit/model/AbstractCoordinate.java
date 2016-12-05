package org.wahlzeit.model;

/**
 * Created by andreas on 21.11.16.
 */
public abstract class AbstractCoordinate implements Coordinate
{

    @Override
    public abstract double getDistance(Coordinate other);

    protected void assertClassInvariants()
    {
        //nothing to do here, no class invariants (but may be overridden in subclass for using methods)
    }

    @Override
    public boolean isEqual(Coordinate other)
    {
        //pre-, postconditions werden in der abstrakten Klasse gecheckt

        if(other == null)
            return false;

        assertClassInvariants();

        double dist = getDistance(other);

        assertClassInvariants();
        return dist < 0.0001;
    }

    //keine Klasseninvariante möglich da keine Attribute


    //Preconditions: coord not null
    //postconditions. radius >= 0, -180<=longitude<=180, -90<=latitiude<=90
    public static SphericCoordinate toSpheric(Coordinate coord)
    {
        if(coord == null)
            return  null;//casting null would also return null

        if(coord instanceof SphericCoordinate)
        {
            return (SphericCoordinate) coord;
        }

        CartesianCoordinate co = (CartesianCoordinate) coord;

        double x = co.getX();
        double y = co.getY();
        double z = co.getZ();

        double radius = Math.sqrt(x*x + y*y + z*z);
        double longitude = Math.toDegrees(Math.atan2(y, x)); //+ 180;//statt -180..180 jetzt 0..360
        double latitude = Math.toDegrees(Math.acos(z/radius));//+90;// statt -90..90 jetzt 0..180

        assert radius >= 0 : "postcondition: radius must be >= 0";
        assert -180 <= longitude && longitude <= 180 : "postcondition: longitude must be between -180 and 180";
        assert -90 <= latitude && latitude <= 90 : "postcondition: latitiude must be between -90 and 90";

        return new SphericCoordinate(latitude, longitude, radius);
    }

    //Preconditions: coord not null
    //postcondition: |x|,|y|,|z| <= r
    public static CartesianCoordinate toCartesian(Coordinate coord)
    {
        if(coord == null)
            return null;//casting null would also return null

        if(coord instanceof CartesianCoordinate)
        {
            return (CartesianCoordinate) coord;
        }

        SphericCoordinate sc = (SphericCoordinate) coord;

        double r = sc.getRadius();
        double latitude = Math.toRadians(sc.getLatitude());
        double longitude = Math.toRadians(sc.getLongitude());

        double x = r * Math.sin(latitude) * Math.cos(longitude);
        double y = r * Math.sin(latitude) * Math.sin(longitude);
        double z = r * Math.cos(latitude);

        assert Math.abs(x) <= r : "postcondition: |x| must be <= r";
        assert Math.abs(y) <= r : "postcondition: |y| must be <= r";
        assert Math.abs(z) <= r : "postcondition: |z| must be <= r";

        return new CartesianCoordinate(x, y, z);
    }




}
