import java.io.*;
import java.net.Socket;

public class Client implements Runnable {

    CommonSwitch cs;

    Client(CommonSwitch cs) {
        this.cs = cs;
    }

    void connect() throws IOException {

        Socket socket = new Socket("127.0.0.1", 7777);
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        DataOutputStream dos = new DataOutputStream(os);
        DataInputStream dis = new DataInputStream(is);

        while (cs.isOn()) {

            //System.out.println(dis.readUTF()==null);
            dos.writeUTF("Hi!");
            dos.writeUTF("Hello!");
            dos.writeUTF("Are you there?");
            //System.out.println(dis.readUTF());
            cs.setOff();

            dos.flush(); //Отключение, чтобы не продолжала висеть связь

        }
        dis.close();
        dos.close();
        socket.close();

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