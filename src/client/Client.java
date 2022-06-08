package client;

import client.parsers.CommandsParser;
import client.parsers.CreateFromConsoleParser;
import util.commands.service.Connect;
import util.sendingUtils.Marks;
import util.sendingUtils.Request;
import util.sendingUtils.Response;
import util.sendingUtils.Serializer;

import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.Scanner;

public class Client {
    private final InetAddress host;
    private DatagramPacket dp;
    private final int port;
    private String login = null;
    private String password = null;
    
    public Client(InetAddress host, int port){
        this.host = host;
        this.port = port;
    }

    public Response sender(byte[] arr){
        Response response = this.connection(arr);
        if (response != null) {
            System.out.println(response);

        }
        return response;
    }

    private Response connection(byte[] arr) {
        byte[] arr1 = Constants.arr;
        Response response = null;
        try(DatagramSocket ds = new DatagramSocket()) {
            dp = new DatagramPacket(arr, arr.length, host, port);
            ds.send(dp);
            ds.setSoTimeout(1000);
            dp = new DatagramPacket(arr1, arr1.length);
            ds.receive(dp);
            response = (Response) Serializer.deserializer(dp.getData());
        } catch (SocketTimeoutException e){
            System.out.println("No response from server");
        } catch (SecurityException e){
            System.out.println("Your security manager doesn't allow you to connect");
        } catch (SocketException e){
            System.out.println("Socket can't be opened");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private boolean checkConnection(){
        boolean ret = false;
        byte[] arr = Serializer.serializer(new Request(new Connect(), null, login, password, Marks.NOTHING)).toByteArray();
        byte[] arr1 = Constants.arr;
        Response response = null;
        try(DatagramSocket ds = new DatagramSocket()) {
            dp = new DatagramPacket(arr, arr.length, host, port);
            ds.send(dp);
            dp = new DatagramPacket(arr1, arr1.length);
            ds.setSoTimeout(1000);
            ds.receive(dp);
            response = (Response) Serializer.deserializer(dp.getData());
        } catch (SocketTimeoutException e){
            System.out.println("No server at this host and port");
        } catch (SecurityException e){
            System.out.println("Your security manager doesn't allow you to connect");
        } catch (SocketException e){
            System.out.println("Socket can't be opened");
        } catch (IOException e) {
            System.out.println("Host is incorrect");
        }
            if (response != null){
                ret = true;
                System.out.println(response);
            }
        return ret;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        HostDefiner hostDefiner = new HostDefiner(in);
        CreateFromConsoleParser createFromConsoleParser = new CreateFromConsoleParser();
        Client client;
        while (true) {
            InetAddress host;
            int port;
            try {
                host = hostDefiner.setHost();
                port = hostDefiner.setPort();
                client = new Client(host , port);
                if (client.checkConnection()){
                    break;
                }
            } catch (NumberFormatException e){
                System.out.println("Wrong port");
            } catch (UnknownHostException e){
                System.out.println("Wrong host");
            }
        }
        while (true) {
            ClientRegister cr = new ClientRegister();
            Request req = cr.validateInput(in);
            Response response;
            boolean flag = false;
            if (req != null) {
                response = client.sender(Serializer.serializer(req).toByteArray());
                if (response.getTitle().equals("Success")){
                    flag = true;
                }
                if (flag){
                    String[] arr = response.getMessage().split(" ");
                    client.setLogin(arr[4]);
                    client.setPassword(arr[9]);
                    break;
                }
            } else {
                break;
            }
        }

        CommandsParser commandsParser = new CommandsParser( createFromConsoleParser, in, client.login, client.password);
        do {
            if (client.login == null){
                System.out.println("You can not execute commands please log in");
                break;
            }
            List<Request> requests = commandsParser.parseCommand();
            if (requests != null) {
                for (Request request : requests) {
                    if (request == null) {
                        break;
                    }
                    client.sender(Serializer.serializer(request).toByteArray());
                }
            }
        } while (!commandsParser.getFlag());

        in.close();


    }
}
