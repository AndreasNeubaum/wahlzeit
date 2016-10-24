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

import com.google.apphosting.api.ApiProxy;

public class Location
{
    public Coordinate coordinate;//?private? --> public im Diagramm...

    /*
    Aggregation "0..1" heißt eigentlich: jede Koordinate darf in HÖCHSTENS einer Location vorkommen
    --> ein Objekt der Klasse Coordinate darf nie zwei Locations zugewiesen werden
    --> wirklich im Code erzwingen?? hoher Aufwnad und eigentlich sinnlos... --> "0..*"?!!!
    * */

    public Location(Coordinate coordinate)
    {
        this.coordinate = coordinate;
    }

    public double getDistance(Location other)
    {
        if(other == null || other.coordinate == null || coordinate == null)
        {
            throw new NullPointerException("One location or coordinate was null");
        }

        return coordinate.getDistance(other.coordinate);
    }
}
