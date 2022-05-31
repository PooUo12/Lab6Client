package util.person;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private static final long serialVersionUID = 3L;
    private final int x;
    private final Long y;
    public Coordinates(int x, Long y){
        this.x = x;
        this.y = y;
    }

    public String toFullString(){
        return (x+","+y);
    }
}
