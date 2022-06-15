package client.parsers;

import util.person.Color;
import util.person.Coordinates;
import util.person.Location;
import util.person.Person;

import java.time.ZonedDateTime;
import java.util.Scanner;

public class CreateFromConsoleParser {


    private static final String PERSON_FIELDS = "util.person name, coordinates x, coordinates y, util.person height, util.person weight, util.person passportID, util.person color,location x, location y, location z, location name";
    private static final String EXAMPLE = "igor,12,12,189,90,2222222222,red,12,12,12,church";

    public Person parse(Scanner in) {
        System.out.println("You are going to write fields, example: \n" + EXAMPLE);
        String[] args = PERSON_FIELDS.split(",");
        int id = -1;
        String personName = stringCheck(in, args[0]);
        int coordinatesX = intCheck(in, args[1]);
        Long coordinatesY = longCheck(in, args[2]);
        ZonedDateTime creationDate = ZonedDateTime.now();
        int height;
        while (true) {
            height = intCheck(in, args[3]);
            if (height > 0) {
                break;
            }
            System.out.println("Height should be > 0");
        }
        double weight;
        while (true) {
            weight = doubleCheck(in, args[4]);
            if (weight > 0) {
                break;
            }
            System.out.println("Weight should be > 0");
        }
        String passportID;
        while (true) {
            passportID = stringCheck(in, args[5]);
            if (passportID.length() >= 8) {
                break;
            }
            System.out.println("Length should be >= 8");
        }
        Color hairColor;
        while (true) {
            String color = stringCheck(in, args[6]);
            if (color.equals("")) {
                hairColor = null;
                break;
            }
            ColorRecognizer colorRecognizer = new ColorRecognizer(color);
            hairColor = colorRecognizer.stringToColor();
            if (hairColor != null) {
                break;
            } else {
                System.out.println("Incorrect color");
            }
        }
        double locationX = doubleCheck(in, args[7]);
        double locationY = doubleCheck(in, args[8]);
        float locationZ = floatCheck(in, args[9]);
        String locationName;
        while (true) {
            locationName = stringCheck(in, args[10]);
            if (locationName.length() < 233) {
                break;
            }
            System.out.println("Length should be < 233");
        }
        Coordinates coordinates = new Coordinates(coordinatesX, coordinatesY);
        Location location = new Location(locationX, locationY, locationZ, locationName);
        return new Person(id, personName, coordinates, creationDate, height, weight, passportID, hairColor, location, null);
    }

    private int intCheck(Scanner in, String output) {
        while (true) {
            System.out.println("Write" + output);
            String next = in.nextLine();
            try {
                return Integer.parseInt(next);
            } catch (NumberFormatException e) {
                System.out.println("Invalid format, try again");
            }
        }
    }

    private Double doubleCheck(Scanner in, String output) {
        while (true) {
            System.out.println("Write " + output);
            String next = in.nextLine();
            try {
                return Double.parseDouble(next);

            } catch (NumberFormatException e) {
                System.out.println("Invalid format, try again");
            }
        }
    }

    private Long longCheck(Scanner in, String output) {
        while (true) {
            System.out.println("Write " + output);
            String next = in.nextLine();
            try {
                return Long.parseLong(next);

            } catch (NumberFormatException e) {
                System.out.println("Invalid format, try again");
            }
        }
    }

    private float floatCheck(Scanner in, String output) {
        while (true) {
            System.out.println("Write " + output);
            String next = in.nextLine();
            try {
                return Float.parseFloat(next);

            } catch (NumberFormatException e) {
                System.out.println("Invalid format, try again");
            }
        }
    }

    private String stringCheck(Scanner in, String output) {
        while (true) {
            System.out.println("Write " + output);
            if (output.equals(" util.person color")) {
                System.out.println("Available colors: red, yellow, orange, white");
                return in.nextLine();
            }
            String next = in.nextLine();
            if (!next.equals("")) {
                return next;
            } else {
                System.out.println("Empty input,that's impossible , try again");

            }
        }
    }

}
