import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    CommonSwitch cs;

    Server(CommonSwitch cs){
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

        ServerSocket serverSocket = new ServerSocket(7777);
        Socket socket = serverSocket.accept();
            System.out.println(cs.isOn()+"1");
        System.out.println(cs.isOn());
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        DataInputStream dis = new DataInputStream(is);
        DataOutputStream dos = new DataOutputStream(os);
        String inputMessage;
        System.out.println(cs.isOn());
        while(cs.isOn()){
            inputMessage = dis.readUTF();
            System.out.println(">"+inputMessage);
            Thread.sleep(111);
            System.out.println(cs.isOn());
        }
        dis.close();
        socket.close();
        serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //String inputLine; while ((inputLine = in.readLine()) != null) System.out.println(inputLine); in.close();

    }

    @Override
    public void run() {

        if(cs.isOn()) try {
            startServer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cs.isOn());
    }
}
