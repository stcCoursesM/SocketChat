import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    public boolean isOn;

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        chatContent += chatContent;
    }

    public String chatContent = "Welcome to the SocketChat!";

    @Override
    public void run() {

        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;

        try {
            serverSocket = new ServerSocket(7777);
            socket = serverSocket.accept();
            is = socket.getInputStream();
            os = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DataInputStream dis = new DataInputStream(is);
        DataOutputStream dos = new DataOutputStream(os);

        while(isOn) {

            try {
                if(dis.readUTF() == "exit") isOn = false;
                setChatContent(getChatContent() + dis.readUTF() + "!");
                String curChatContent = getChatContent();
                System.out.println(curChatContent);
                dos.writeUTF(curChatContent);
                Thread.sleep(1000);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
