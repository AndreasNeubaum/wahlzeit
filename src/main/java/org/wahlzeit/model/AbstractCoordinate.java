package org.wahlzeit.model;

/**
 * Created by andreas on 21.11.16.
 */
public abstract class AbstractCoordinate implements Coordinate
{

    @Override
    public abstract double getDistance(Coordinate other);

    @Override
    public abstract boolean isEqual(Coordinate other);

    public static SphericCoordinate toSpheric(Coordinate coord)
    {
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

        return new SphericCoordinate(latitude, longitude, radius);
    }

    public static CartesianCoordinate toCartesian(Coordinate coord)
    {
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

        return new CartesianCoordinate(x, y, z);
    }




}
