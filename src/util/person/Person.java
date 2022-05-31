package util.person;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class Person implements Comparable<Person>, Serializable {
    private static final long serialVersionUID = 0L;
    private int id;
    private final String name;
    private final Coordinates coordinates;
    private ZonedDateTime creationDate;
    private final int height;
    private final Double weight;
    private final String passportID;
    private final Color hairColor;
    private final Location location;

    public Person(int id, String name, Coordinates coordinates, ZonedDateTime creationDate, int height, Double weight, String passportID, Color hairColor, Location location){
        this.creationDate = creationDate;
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.height = height;
        this.weight = weight;
        this.passportID = passportID;
        this.hairColor = hairColor;
        this.location = location;
    }



    public int getId(){
        return id;
    }

    public int compareTo(Person p){
        return this.id - p.getId();
    }

    public Double getWeight(){
        return this.weight;
    }

    public int getHeight(){
        return this.height;
    }

    public String getPassportID(){
        return this.passportID;
    }

    public String toString(){
        return(id+","+name+","+coordinates.toFullString()+","+ creationDate.toString() + "," +height+","+weight+","+passportID+","+hairColor+","+location.toFullString());
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreationDate() {
        creationDate =  ZonedDateTime.now();
    }
}

