package util.sendingUtils;

import util.commands.Command;

import java.io.Serializable;
import java.util.List;

public class Request implements Serializable {
    private static final long serialVersionUID = 10L;
    private final Command command;
    private final List<Object> args;


    public Request(Command command, List<Object> args){
        this.command = command;
        this.args = args;

    }

    public Command getCommand() {
        return command;
    }

    public List<Object> getArgs() {
        return args;
    }


    @Override
    public String toString() {
        return "Request{" +
                "command=" + command +
                ", args=" + args +
                '}';
    }
}

