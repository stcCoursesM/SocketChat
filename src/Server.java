import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    CommonSwitch cs;

    Server(CommonSwitch cs) {
        this.cs = cs;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        chatContent += chatContent;
    }

    public String chatContent = "Welcome to the SocketChat!";

    public void startServer() throws InterruptedException {

        try {

            ServerSocket serverSocketIn = new ServerSocket(7777);

            Socket socketOut = new Socket("127.0.0.1", 5555);

            Socket socketIn = serverSocketIn.accept();


            InputStream is = socketIn.getInputStream();
            OutputStream os = socketOut.getOutputStream();
            DataInputStream dis = new DataInputStream(is);
            DataOutputStream dos = new DataOutputStream(os);

            String inputMessage;

            while (cs.isOn()) {
                dos.writeUTF("Ping!");
                inputMessage = dis.readUTF();
                if (inputMessage != null) System.out.println(">" + inputMessage);
                Thread.sleep(111);
                dos.flush();
            }
            dis.close();
            dos.close();
            socketIn.close();
            socketOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //String inputLine; while ((inputLine = in.readLine()) != null) System.out.println(inputLine); in.close();

    }

    @Override
    public void run() {

        if (cs.isOn()) try {
            startServer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cs.isOn());
    }
}
