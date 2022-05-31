package util.person;

import java.io.Serializable;

public class Location implements Serializable {
    private static final long serialVersionUID = 2L;
    private final Double x;
    private final Double y;
    private final float z;
    private final String name;

    public Location(Double x, Double y, float z, String name){
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }
    public String toFullString(){
        return (x+","+y+","+z+ ","+ name);
    }
}
