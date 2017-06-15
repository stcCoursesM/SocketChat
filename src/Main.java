public class Main {

    public static void main(String[] args) {

        CommonSwitch cs = new CommonSwitch();
        cs.setOn();

        Server srv = new Server(cs);
        new Thread(srv).start();

        Client cl1 = new Client(cs);
        new Thread(cl1).start();

    }

}
