package org.wahlzeit.model;

/*
* Location
*
* V 1.0
*
* 24.10.2016
*
* Copyright notice - none
*/


import com.googlecode.objectify.annotation.Ignore;




public class Location
{
    //@Ignore//ignore in objectify service
    //Interface doesnt work in persistance ofy service --> use concrete class and transform
    public SphericCoordinate coordinate;

    /*
    Aggregation "0..1" heißt eigentlich: jede Koordinate darf in HÖCHSTENS einer Location vorkommen
    --> ein Objekt der Klasse Coordinate darf nie zwei Locations zugewiesen werden
    --> wirklich im Code erzwingen?? hoher Aufwnad und eigentlich sinnlos... --> "0..*"?!!!
    * */

    public Location(Coordinate coordinate)
    {
        if(coordinate == null)
        {
            throw new IllegalArgumentException("coordinate must not be null");
        }

        this.coordinate = AbstractCoordinate.toSpheric(coordinate);
    }

    //preconditions: coordinate and other.coordinate must not be null
    public double getDistance(Location other)
    {
        if(other == null)
        {
            throw new IllegalArgumentException("other was null");
        }

        assert other.coordinate != null : "precondition: other.coordinate must not be null";
        assert coordinate != null : "precondition: coordinate must not be null";

        return coordinate.getDistance(other.coordinate);
    }
}
