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

public class Coordinate
{
    private double latitude;
    private double longitude;
    private static final double EarthRadius = 6371;

    public Coordinate(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    private static double DegToRad(double degrees)
    {
        return (degrees/180)*Math.PI;
    }

    public double getDistance(Coordinate other)
    {
        if(other==null)
        {
            throw new NullPointerException("other must not be null");
        }

        double otherLatitudeRad = DegToRad(other.latitude);
        double ownLatitudeRad = DegToRad(latitude);
        double otherLatitudeSin = Math.sin(otherLatitudeRad);
        double otherLatitudeCos = Math.cos(otherLatitudeRad);
        double ownLatitudeCos = Math.cos(ownLatitudeRad);
        double ownLatitudeSin = Math.sin(ownLatitudeRad);


        double absoluteLongtitudeDiff = Math.abs(longitude - other.longitude)%360;
        double longitudeAbsDiffRad = DegToRad(Math.min(360-absoluteLongtitudeDiff, absoluteLongtitudeDiff));

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

        double distance = EarthRadius * deltaSigma;

        return distance;
    }
}
