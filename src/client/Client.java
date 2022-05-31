package client;

import client.parsers.CommandsParser;
import client.parsers.CreateFromConsoleParser;
import util.commands.Connect;
import util.sendingUtils.Request;
import util.sendingUtils.Response;
import util.sendingUtils.Serializer;

import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.Scanner;

public class Client {
    InetAddress host;
    DatagramPacket dp;
    int port;
    
    public Client(InetAddress host, int port){
        this.host = host;
        this.port = port;
    }

    public void sender(byte[] arr){
        Response response = this.connection(arr);
        if (response != null) {
            System.out.println(response);
        }


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

    public boolean checkConnection(){
        boolean ret = false;
        byte[] arr = Serializer.serializer(new Request(new Connect(), null)).toByteArray();
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
            e.printStackTrace();
        }
            if (response != null){
                ret = true;
                System.out.println(response);
            }
        return ret;
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        HostDefiner hostDefiner = new HostDefiner(in);
        CreateFromConsoleParser createFromConsoleParser = new CreateFromConsoleParser();
        CommandsParser commandsParser = new CommandsParser( createFromConsoleParser, in);
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
        do {
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
