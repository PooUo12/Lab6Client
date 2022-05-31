package client.parsers;


import util.person.Color;


public class ColorRecognizer {
    private final String color;
    private final static String RED = "red";
    private final static String YELLOW = "yellow";
    private final static String ORANGE = "orange";
    private final static String WHITE = "white";

    public ColorRecognizer(String color){
        this.color = color;
    }

    public Color stringToColor(){
        Color colorForReturn = null;
        switch (color.toLowerCase()){
            case RED:
                colorForReturn = Color.RED;
                break;
            case YELLOW:
                colorForReturn = Color.YELLOW;
                break;
            case ORANGE:
                colorForReturn = Color.ORANGE;
                break;
            case WHITE:
                colorForReturn = Color.WHITE;
                break;
        }
        return colorForReturn;
    }
}
