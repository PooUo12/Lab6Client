package client.parsers;

import util.commands.*;
import util.exceptions.InvalidCommandException;
import util.person.Person;
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

    public CommandsParser( CreateFromConsoleParser createFromConsoleParser, Scanner in){
        this.createFromConsoleParser = createFromConsoleParser;
        this.in = in;
    }

    public List<Request> parseCommand(){
        List<Request> requests = new ArrayList<>();
        Request request;
        Person person;
        List<Object> args = new ArrayList<>();
        System.out.println("Enter your command:");
        try {
            String[] command = in.nextLine().split(" ");

        switch (command[0]){
            case HELP:
                Command help = new Help();
                request = new Request(help, null);
                requests.add(request);
                return requests;
            case INFO:
                Command info = new Info();
                request = new Request(info, null);
                requests.add(request);
                return requests;
            case SHOW:
                Command show = new Show();
                request = new Request(show, null);
                requests.add(request);
                return requests;
            case ADD:
                person = createFromConsoleParser.parse(in);
                Command add = new Add();
                args.add(person);
                request = new Request(add, args);
                requests.add(request);
                return requests;
            case UPDATE:
                person = createFromConsoleParser.parse(in);
                Command update = new Update();
                args.add(person);
                args.add(Integer.parseInt(command[1]));
                request = new Request(update, args);
                requests.add(request);
                return requests;
            case REMOVE_BY_ID:
                Command removeById = new RemoveById();
                args.add(Integer.parseInt(command[1]));
                request = new Request(removeById, args);
                requests.add(request);
                return requests;
            case CLEAR:
                Command clear = new Clear();
                request = new Request(clear, null);
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
                request = new Request(addIfMax, args);
                requests.add(request);
                return requests;
            case ADD_IF_MIN:
                Command addIfMin = new AddIfMin();
                person = createFromConsoleParser.parse(in);
                args.add(person);
                request = new Request(addIfMin, args);
                requests.add(request);
                return requests;
            case REMOVE_LOWER:
                Command removeLower = new RemoveLower();
                person = createFromConsoleParser.parse(in);
                args.add(person);
                request = new Request(removeLower, args);
                requests.add(request);
                return requests;
            case REMOVE_ALL_BY_WEIGHT:
                Command removeAllByWeight = new RemoveAllByWeight();
                args.add(Integer.parseInt(command[1]));
                request = new Request(removeAllByWeight, args);
                requests.add(request);
                return requests;
            case COUNT_GREATER_THAN_HEIGHT:
                Command countGreaterThanHeight = new CountGreaterThanHeight();
                args.add(Integer.parseInt(command[1]));
                request = new Request(countGreaterThanHeight, args);
                requests.add(request);
                return requests;
            case FILTER_STARTS_WITH_PASSPORT_I_D:
                Command filterStartsWithPassportID = new FilterStartsWithPassportID();
                args.add(command[1]);
                request = new Request(filterStartsWithPassportID, args);
                requests.add(request);
                return requests;
            default:
                throw new InvalidCommandException("Invalid command");

        }
        } catch (InvalidCommandException e){
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Argument is absent");
        } catch(NumberFormatException e){
            System.out.println("Argument has wrong format");
        } catch (NoSuchElementException e){
            System.out.println("You input command+D, stopping the program");
            this.flag = true;
        }
        return null;

    }

    public List<Request> parseCommand(String line){
        String[] command = line.split(" ");
        Request request;
        List<Request> requests = new ArrayList<>();
        List<Object> args = new ArrayList<>();
        Person person;

        try{
            switch (command[0]){
                case HELP:
                    Command help = new Help();
                    request = new Request(help, null);
                    requests.add(request);
                    return requests;
                case INFO:
                    Command info = new Info();
                    request = new Request(info, null);
                    requests.add(request);
                    return requests;
                case SHOW:
                    Command show = new Show();
                    request = new Request(show, null);
                    requests.add(request);
                    return requests;
                case ADD:
                    person = createFromConsoleParser.parse(in);
                    Command add = new Add();
                    args.add(person);
                    request = new Request(add, args);
                    requests.add(request);
                    return requests;
                case UPDATE:
                    person = createFromConsoleParser.parse(in);
                    Command update = new Update();
                    args.add(person);
                    args.add(Integer.parseInt(command[1]));
                    request = new Request(update, args);
                    requests.add(request);
                    return requests;
                case REMOVE_BY_ID:
                    Command removeById = new RemoveById();
                    args.add(Integer.parseInt(command[1]));
                    request = new Request(removeById, args);
                    requests.add(request);
                    return requests;
                case CLEAR:
                    Command clear = new Clear();
                    request = new Request(clear, null);
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
                    request = new Request(addIfMax, args);
                    requests.add(request);
                    return requests;
                case ADD_IF_MIN:
                    Command addIfMin = new AddIfMin();
                    person = createFromConsoleParser.parse(in);
                    args.add(person);
                    request = new Request(addIfMin, args);
                    requests.add(request);
                    return requests;
                case REMOVE_LOWER:
                    Command removeLower = new RemoveLower();
                    person = createFromConsoleParser.parse(in);
                    args.add(person);
                    request = new Request(removeLower, args);
                    requests.add(request);
                    return requests;
                case REMOVE_ALL_BY_WEIGHT:
                    Command removeAllByWeight = new RemoveAllByWeight();
                    args.add(Integer.parseInt(command[1]));
                    request = new Request(removeAllByWeight, args);
                    requests.add(request);
                    return requests;
                case COUNT_GREATER_THAN_HEIGHT:
                    Command countGreaterThanHeight = new CountGreaterThanHeight();
                    args.add(Integer.parseInt(command[1]));
                    request = new Request(countGreaterThanHeight, args);
                    requests.add(request);
                    return requests;
                case FILTER_STARTS_WITH_PASSPORT_I_D:
                    Command filterStartsWithPassportID = new FilterStartsWithPassportID();
                    args.add(command[1]);
                    request = new Request(filterStartsWithPassportID, args);
                    requests.add(request);
                    return requests;
                default:
                    throw new InvalidCommandException("Invalid command");


            }
        } catch (InvalidCommandException e){
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Argument is absent");
        } catch (NumberFormatException e){
            System.out.println("Argument has wrong format");
        }
        return null;
    }


    public boolean getFlag(){
        return flag;
    }
}
