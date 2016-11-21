package org.wahlzeit.model;


/**
 * Created by andreas on 14.11.16.
 */

public class CartesianCoordinate extends AbstractCoordinate
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
        CartesianCoordinate cOther = toCartesian(other);
        return this.getDistance(cOther);
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

    @Override
    public boolean isEqual(Coordinate other)
    {
        double dist = getDistance(other);
        return dist < 0.0001;
    }
}
