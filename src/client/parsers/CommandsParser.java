package client.parsers;

import util.commands.*;
import util.commands.client.*;
import util.exceptions.InvalidCommandException;
import util.person.Person;
import util.sendingUtils.Marks;
import util.sendingUtils.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static client.Constants.*;

public class CommandsParser {

    private final CreateFromConsoleParser createFromConsoleParser;
    private final Scanner in;
    private boolean flag = false;
    private final ScriptParser scriptParser = new ScriptParser(this);
    private final String login;
    private final String password;

    public CommandsParser(CreateFromConsoleParser createFromConsoleParser, Scanner in, String login, String password) {
        this.createFromConsoleParser = createFromConsoleParser;
        this.in = in;
        this.login = login;
        this.password = password;
    }

    public List<Request> parseCommand() {
        List<Request> requests = new ArrayList<>();
        List<Object> args = new ArrayList<>();
        System.out.println("Enter your command:");
        try {
            String[] command = in.nextLine().split(" ");

            return getRequests(requests, args, command);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Argument is absent");
        } catch (NumberFormatException e) {
            System.out.println("Argument has wrong format");
        } catch (NoSuchElementException e) {
            System.out.println("You input command+D, stopping the program");
            this.flag = true;
        }
        return null;

    }

    private List<Request> getRequests(List<Request> requests, List<Object> args, String[] command) {
        Request request;
        Person person;
        switch (command[0]) {
            case HELP:
                Command help = new Help();
                request = new Request(help, null, login, password, Marks.NOTHING);
                requests.add(request);
                return requests;
            case INFO:
                Command info = new Info();
                request = new Request(info, null, login, password, Marks.READ);
                requests.add(request);
                return requests;
            case SHOW:
                Command show = new Show();
                request = new Request(show, null, login, password, Marks.READ);
                requests.add(request);
                return requests;
            case ADD:
                person = createFromConsoleParser.parse(in);
                Command add = new Add();
                args.add(person);
                request = new Request(add, args, login, password, Marks.WRITE);
                requests.add(request);
                return requests;
            case UPDATE:
                person = createFromConsoleParser.parse(in);
                Command update = new Update();
                args.add(person);
                args.add(Integer.parseInt(command[1]));
                request = new Request(update, args, login, password, Marks.WRITE);
                requests.add(request);
                return requests;
            case REMOVE_BY_ID:
                Command removeById = new RemoveById();
                args.add(Integer.parseInt(command[1]));
                request = new Request(removeById, args, login, password, Marks.WRITE);
                requests.add(request);
                return requests;
            case CLEAR:
                Command clear = new Clear();
                request = new Request(clear, null, login, password, Marks.WRITE);
                requests.add(request);
                return requests;
            case EXECUTE_SCRIPT:
                requests = scriptParser.parse(command[1]);
                return requests;
            case EXIT:
                flag = true;
                return null;
            case ADD_IF_MAX:
                Command addIfMax = new AddIfMax();
                person = createFromConsoleParser.parse(in);
                args.add(person);
                request = new Request(addIfMax, args, login, password, Marks.WRITE);
                requests.add(request);
                return requests;
            case ADD_IF_MIN:
                Command addIfMin = new AddIfMin();
                person = createFromConsoleParser.parse(in);
                args.add(person);
                request = new Request(addIfMin, args, login, password, Marks.WRITE);
                requests.add(request);
                return requests;
            case REMOVE_LOWER:
                Command removeLower = new RemoveLower();
                person = createFromConsoleParser.parse(in);
                args.add(person);
                request = new Request(removeLower, args, login, password, Marks.WRITE);
                requests.add(request);
                return requests;
            case REMOVE_ALL_BY_WEIGHT:
                Command removeAllByWeight = new RemoveAllByWeight();
                args.add(Integer.parseInt(command[1]));
                request = new Request(removeAllByWeight, args, login, password, Marks.WRITE);
                requests.add(request);
                return requests;
            case COUNT_GREATER_THAN_HEIGHT:
                Command countGreaterThanHeight = new CountGreaterThanHeight();
                args.add(Integer.parseInt(command[1]));
                request = new Request(countGreaterThanHeight, args, login, password, Marks.READ);
                requests.add(request);
                return requests;
            case FILTER_STARTS_WITH_PASSPORT_I_D:
                Command filterStartsWithPassportID = new FilterStartsWithPassportID();
                args.add(command[1]);
                request = new Request(filterStartsWithPassportID, args, login, password, Marks.READ);
                requests.add(request);
                return requests;
            default:
                throw new InvalidCommandException("Invalid command");

        }
    }

    public List<Request> parseCommand(String line) {
        String[] command = line.split(" ");
        List<Request> requests = new ArrayList<>();
        List<Object> args = new ArrayList<>();

        try {
            return getRequests(requests, args, command);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Argument is absent");
        } catch (NumberFormatException e) {
            System.out.println("Argument has wrong format");
        }
        return null;
    }


    public boolean getFlag() {
        return flag;
    }
}
