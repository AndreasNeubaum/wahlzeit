package org.wahlzeit.model;


/*
* Coordinate
*
* V 1.0
*
* 24.10.2016
*
* Copyright notice - none
*/
public class SphericCoordinate implements Coordinate
{
    private double latitude;
    private double longitude;
    private double radius;
    public static final double EarthRadius = 6371;

    public SphericCoordinate(double latitude, double longitude, double radius)
    {
        if(radius <= 0)
        {
            throw new IllegalArgumentException("radius must not be <= 0");
        }

        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    public SphericCoordinate(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = EarthRadius;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public double getRadius() { return radius; }

    private double getDistance(SphericCoordinate other)
    {
        if(other==null)
        {
            throw new NullPointerException("other must not be null");
        }

        if(Math.abs(radius - other.radius) / Math.max(radius, other.radius) > 0.000001)
        {
            throw new IllegalArgumentException("both spheres have a different or too small radius");
        }

        double otherLatitudeRad = Math.toRadians(other.latitude);
        double ownLatitudeRad = Math.toRadians(latitude);
        double otherLatitudeSin = Math.sin(otherLatitudeRad);
        double otherLatitudeCos = Math.cos(otherLatitudeRad);
        double ownLatitudeCos = Math.cos(ownLatitudeRad);
        double ownLatitudeSin = Math.sin(ownLatitudeRad);


        double absoluteLongtitudeDiff = Math.abs(longitude - other.longitude)%360;
        double longitudeAbsDiffRad = Math.toRadians(Math.min(360-absoluteLongtitudeDiff, absoluteLongtitudeDiff));

        double longitudeAbsDiffSin = Math.sin(longitudeAbsDiffRad);
        double longitudeAbsDiffCos = Math.cos(longitudeAbsDiffRad);

        double firstBracketSquare = otherLatitudeCos*longitudeAbsDiffSin;
        firstBracketSquare *= firstBracketSquare;

        double otherLatitudeCosLongitudeAbsDiffCos = otherLatitudeCos * longitudeAbsDiffCos;

        double secondBracketSquare = ownLatitudeCos * otherLatitudeSin - ownLatitudeSin * otherLatitudeCosLongitudeAbsDiffCos;
        secondBracketSquare *= secondBracketSquare;

        double numerator = Math.sqrt(firstBracketSquare + secondBracketSquare);

        double denominator = otherLatitudeSin * ownLatitudeSin + ownLatitudeCos * otherLatitudeCosLongitudeAbsDiffCos;

        double deltaSigma = Math.atan2(numerator, denominator);

        double distance = radius * deltaSigma;

        return distance;
    }

    @Override
    public double getDistance(Coordinate coordinate)
    {
        if(!(coordinate instanceof SphericCoordinate))
            throw new IllegalArgumentException("coordinate is not a spheric coordinate");

        return getDistance((SphericCoordinate) coordinate);
    }
}
