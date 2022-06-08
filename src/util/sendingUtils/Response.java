package util.sendingUtils;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = 11L;
    private final String title;
    private final String message;
    public Response(String title, String message){
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title + " : " + message;
    }

    public String getMessage() {
        return message;
    }
}
