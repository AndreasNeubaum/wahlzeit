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

//--> Klasseninvariante: x==x, y==y, z==z, x!=

public class CartesianCoordinate extends AbstractCoordinate
{
    private double x;
    private double y;
    private double z;

    @Override
    protected void assertClassInvariants()
    {
        //siehe Kommentar oben bzgl. x==x Check

        assert x == x : "x must not be double.NaN";
        assert y == y : "y must not be double.NaN";
        assert z == z : "z must not be double.NaN";

        assert x != Double.NEGATIVE_INFINITY : "x must not be negative infinity";
        assert x != Double.POSITIVE_INFINITY : "x must not be positive infinity";

        assert y != Double.NEGATIVE_INFINITY : "y must not be negative infinity";
        assert y != Double.POSITIVE_INFINITY : "y must not be positive infinity";

        assert z != Double.NEGATIVE_INFINITY : "z must not be negative infinity";
        assert z != Double.POSITIVE_INFINITY : "z must not be positive infinity";
    }

    public CartesianCoordinate(double x, double y, double z)
    {
        //precondition entfällt (siehe Klasseninvariante), Zuweiseung wird wohl klappen wenn nicht ist mehr kaputt...
        this.x = x;
        this.y = y;
        this.z = z;

        assertClassInvariants();
    }

    public double getX()
    {
        assertClassInvariants();
        return x;
    }

    public double getY()
    {
        assertClassInvariants();
        return y;
    }

    public double getZ()
    {
        assertClassInvariants();
        return z;
    }

    //preconditions: other != null
    //postconditions: distance >= 0
    @Override
    public double getDistance(Coordinate other)
    {
        //keine Modulgrenze, nur interne Nutzung --> keine exception werfen, precondition stattdessen
        assert other != null : "precondition: other must not be null";

        assertClassInvariants();

        CartesianCoordinate cOther = toCartesian(other);

        double distance = this.getDistance(cOther);
        assert distance >= 0 : "postcondition: distance mut be >= 0";
        return distance;
    }

    //precondition: other != null
    //postcondition: distance >= 0
    private double getDistance(CartesianCoordinate other)
    {
        assert other != null : "precondition: other must not be null";

        double deltaXSquared = x - other.x;
        deltaXSquared *= deltaXSquared;

        double deltaYSquared = y - other.y;
        deltaYSquared *= deltaYSquared;

        double deltaZSquared = z - other.z;
        deltaZSquared *= deltaZSquared;

        double distance = Math.sqrt(deltaXSquared + deltaYSquared + deltaZSquared);

        assert distance >= 0 : "postcondition: distance must be >= 0";

        return distance;
    }

}
