package client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class HostDefiner {
    private final Scanner scanner;
    public HostDefiner(Scanner scanner){
        this.scanner = scanner;
    }

    public InetAddress setHost() throws UnknownHostException {
        InetAddress port;
        System.out.println("Write host, example:" + InetAddress.getLocalHost().toString().substring(26));
        port = InetAddress.getByName(scanner.nextLine());
        return port;
    }

    public int setPort() throws NumberFormatException{
        int host;
        System.out.println("Write port");
        host = Integer.parseInt(scanner.nextLine());
        return host;
    }
}
