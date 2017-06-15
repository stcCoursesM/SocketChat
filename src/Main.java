public class Main {

    public static void main(String[] args) {


        Server srv = new Server();
        Thread thrSrv = new Thread(srv);
        thrSrv.start();

        Client cl1 = new Client();
        Thread thrCli1 = new Thread(cl1);
        thrCli1.start();


    }




}
