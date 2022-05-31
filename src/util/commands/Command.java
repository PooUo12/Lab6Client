package util.commands;

import util.sendingUtils.Response;

import java.io.Serializable;
import java.util.List;

public abstract class Command implements Serializable {
    private static final long serialVersionUID = 1L;
    public abstract Response execute(List<Object> params);



}
