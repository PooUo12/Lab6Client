package client.parsers;

import util.sendingUtils.Request;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Class that parse scripts from file
 */
public class ScriptParser {
    private final CommandsParser commandsParser;
    private int i = 0;
    /**
     * @param commandsParser Parser and executor of util.commands
     */
    public ScriptParser(CommandsParser commandsParser){
        this.commandsParser = commandsParser;
    }

    /**
     * @param scriptName name of the script file
     */
    public List<Request> parse(String scriptName){
        List<Request> requests = new ArrayList<>();
        try {
            i++;
            if (i >= 5) {
                throw new RuntimeException("Impossible to use so many inner scripts");
            }
            String line;
            BufferedInputStream buff = new BufferedInputStream(new FileInputStream(scriptName));
            Scanner s = new Scanner(buff);

            while(s.hasNext()){
                line = s.nextLine();
                if (line.equals("execute_script " + scriptName)){
                    System.out.println("Impossible to use same script from script");
                    break;
                }

                List<Request> reqs = commandsParser.parseCommand(line);
                requests.addAll(reqs);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Incorrect file name");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return requests;
    }
}
