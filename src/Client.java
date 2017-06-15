import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client implements Runnable {

    CommonSwitch cs;

    Client(CommonSwitch cs) {
        this.cs = cs;
    }

    void connect() throws IOException, InterruptedException {


        ServerSocket serverSocketIn = new ServerSocket(5555);
        Socket socketIn = serverSocketIn.accept();
        Socket socketOut = new Socket("127.0.0.1", 7777);

        OutputStream os = socketOut.getOutputStream();
        InputStream is = socketIn.getInputStream();
        DataOutputStream dos = new DataOutputStream(os);
        DataInputStream dis = new DataInputStream(is);

        String inputMessage;

        while (cs.isOn()) {

            //System.out.println(dis.readUTF()==null);
            dos.writeUTF("Hi!");
//            dos.writeUTF("Hello!");
//            dos.writeUTF("Are you there?");
            inputMessage = dis.readUTF();
            if (inputMessage != null) System.out.println(">" + inputMessage);
//            inputMessage = dis.readUTF();
//            System.out.println(">"+inputMessage);
//            inputMessage = dis.readUTF();
//            System.out.println(">"+inputMessage);
            Thread.sleep(111);
            //System.out.println(dis.readUTF());
            cs.setOff();

            dos.flush();
        }
        dis.close();
        dos.close();
        socketIn.close();
        socketOut.close();

    }

    @Override
    public void run() {

        try {
            connect();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}