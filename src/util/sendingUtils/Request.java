package util.sendingUtils;

import util.commands.Command;

import java.io.Serializable;
import java.util.List;

public class Request implements Serializable {
    private static final long serialVersionUID = 10L;
    private final Command command;
    private final List<Object> args;
    private final String login;
    private final String password;
    private final Marks mark;


    public Request(Command command, List<Object> args, String login, String password, Marks mark){
        this.command = command;
        this.args = args;
        this.login = login;
        this.password = password;
        this.mark = mark;
    }

    public Command getCommand() {
        return command;
    }

    public List<Object> getArgs() {
        return args;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Marks getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return "Request{" +
                "command=" + command +
                ", args=" + args +
                '}';
    }
}

