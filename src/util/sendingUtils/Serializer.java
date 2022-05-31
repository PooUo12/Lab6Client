package util.sendingUtils;

import java.io.*;

public class Serializer {
    public static ByteArrayOutputStream serializer(Object request) {
        ByteArrayOutputStream bos;
        ObjectOutputStream oos;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(request);
            oos.close();
            return bos;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static Object deserializer(byte[] request){
        ByteArrayInputStream bis;
        ObjectInputStream ois;

        try {
            bis = new ByteArrayInputStream(request,0, request.length);
            ois = new ObjectInputStream(bis);
            Object obj = ois.readObject();
            ois.close();
            return obj;
        } catch (IOException| ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }
}
