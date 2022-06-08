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
    private String user;

    public Person(int id, String name, Coordinates coordinates, ZonedDateTime creationDate, int height, Double weight, String passportID, Color hairColor, Location location, String user){
        this.creationDate = creationDate;
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.height = height;
        this.weight = weight;
        this.passportID = passportID;
        this.hairColor = hairColor;
        this.location = location;
        this.user = user;
    }



    public int getId(){
        return id;
    }

    public int compareTo(Person p){
            return name.compareTo(p.getName());
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
        return(id+","+name+","+coordinates.toFullString()+","+ creationDate.toString() + "," +height+","+weight+","+passportID+","+hairColor+","+location.toFullString() + "," + user);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreationDate() {
        creationDate =  ZonedDateTime.now();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public String getHairColor() {
        return hairColor.toString();
    }

    public Location getLocation() {
        return location;
    }
}

