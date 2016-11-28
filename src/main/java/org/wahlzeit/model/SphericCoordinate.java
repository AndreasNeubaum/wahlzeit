package org.wahlzeit.model;


//JavaStandard:
// 1) (double.NaN == double.NaN) = false IMMER
// 2) x != x --> x == double.NaN !!!
// --> nein, der Test x!=x ist nicht paranoid sonder ein Test auf double.NaN :)
        /*
        Java Standard Zitat:
        "As has already been described, NaN is unordered, so a numeric comparison operation involving one or two NaNs
         returns false and any != comparison involving NaN returns true, including x!=x when x is NaN."
        * */

/*
* Vergleich auf +inf und -inf geht wieder erwartungsgemäß:
*
* Java Standard Zitat:
* "In particular, there is one value representing positive infinity and one value representing negative infinity;
 * each compares equal only to itself, and each compares unequal to all other values."
* */

//Klaseninvariante: r>=0, -180<=longitude<=180, -90<=latitude<=90, longitude==longitude, latitude==latitude, radius==radius
public class SphericCoordinate extends AbstractCoordinate
{
    private double latitude;
    private double longitude;
    private double radius;
    public static final double EarthRadius = 6371;

    @Override
    protected void assertClassInvariants()
    {
        assert radius >= 0 : "class invariant: radius >= 0";
        assert -180 <= longitude && longitude <= 180 : "class invariant: -180<=longitude<=180";
        assert -90 <=latitude && latitude <= 90 : "class invariant: -90<=latitude<=90";

        assert latitude == latitude : "latitude must not be double.NaN";
        assert longitude == longitude : "longitude must not be double.NaN";
        assert radius == radius : "radius must not be double.NaN";
    }

    //precondition: r>=0, -180<=longitude<=180, -90<=latitude<=90
    public SphericCoordinate(double latitude, double longitude, double radius)
    {
        assert radius >= 0 : "class invariant: radius >= 0";
        assert -180 <= longitude && longitude <= 180 : "class invariant: -180<=longitude<=180";
        assert -90 <=latitude && latitude <= 90 : "class invariant: -90<=latitude<=90";

        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;

        //postconditions = preconditions -> hier sinnlos
        assertClassInvariants();
    }

    //precondition: -180<=longitude<=180, -90<=latitude<=90
    public SphericCoordinate(double latitude, double longitude)
    {
        assert -180 <= longitude && longitude <= 180 : "class invariant: -180<=longitude<=180";
        assert -90 <=latitude && latitude <= 90 : "class invariant: -90<=latitude<=90";

        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = EarthRadius;

        //postconditions = preconditions + class invariants -> hier sinnlos
        assertClassInvariants();
    }

    public double getLatitude()
    {
        assertClassInvariants();
        return latitude;
    }

    public double getLongitude()
    {
        assertClassInvariants();
        return longitude;
    }

    public double getRadius()
    {
        assertClassInvariants();
        return radius;
    }

    //preconditions: other != null, Math.abs(radius - other.radius) / Math.max(radius, other.radius) > 0.000001
    //postconditions: distance >= 0
    private double getDistance(SphericCoordinate other)
    {
        assert other != null : "precondition: other must not be null";
        assert Math.abs(radius - other.radius) / Math.max(radius, other.radius) <= 0.000001 :
                "precondition: both spheres must have the same radius which must nut be too small";

        assertClassInvariants();

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

        assert distance >= 0 : "postcondition: distance must be >= 0";
        assertClassInvariants();

        return distance;
    }

    //preconditions: other != null
    //postconditions: distance >= 0
    @Override
    public double getDistance(Coordinate other)
    {
        assert other != null : "precondition: other must not be null";
        assertClassInvariants();

        SphericCoordinate sOther = toSpheric(other);
        assertClassInvariants();

        double distance = this.getDistance(sOther);
        assert distance >= 0 : "postcondition: distance mut be >= 0";
        return distance;
    }

}
