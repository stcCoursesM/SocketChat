import java.io.*;
import java.net.Socket;

public class Client implements Runnable{

    void connect() throws IOException {

        Socket socket = new Socket("127.0.0.1", 7777);

        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        DataOutputStream dos = new DataOutputStream(os);
        DataInputStream dis = new DataInputStream(is);

        System.out.println(dis.readUTF()==null);
        dos.writeUTF("Hi!");
        System.out.println(dis.readUTF());

        dos.flush(); //Отключение, чтобы не продолжала висеть связь
        dis.close();
    }

    @Override
    public void run() {

        try {
            connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}