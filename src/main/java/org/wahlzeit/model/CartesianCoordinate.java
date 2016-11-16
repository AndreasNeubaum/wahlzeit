package org.wahlzeit.model;


/**
 * Created by andreas on 14.11.16.
 */

public class CartesianCoordinate implements Coordinate
{
    private double x;
    private double y;
    private double z;

    public CartesianCoordinate(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getZ()
    {
        return z;
    }

    @Override
    public double getDistance(Coordinate other)
    {
        if(!(other instanceof CartesianCoordinate))
        {
            throw new IllegalArgumentException("other coordinate must be a cartesian coordinate");
        }

        return getDistance((CartesianCoordinate) other);
    }

    private double getDistance(CartesianCoordinate other)
    {
        double deltaXSquared = x - other.x;
        deltaXSquared *= deltaXSquared;

        double deltaYSquared = y - other.y;
        deltaYSquared *= deltaYSquared;

        double deltaZSquared = z - other.z;
        deltaZSquared *= deltaZSquared;

        double distance = Math.sqrt(deltaXSquared + deltaYSquared + deltaZSquared);

        return distance;
    }
}
