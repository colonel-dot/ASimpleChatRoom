import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class SendingPort{
    private User user;

    public SendingPort(User user) {
        this.user = user;
    }


    public static void main(String[] args) throws Exception {
        User user1 = new User("猴哥");
        User user2 = new User("八戒");
        Sending client1 = new Sending(user1);
        Sending client2 = new Sending(user2);
        Thread t1 = new Thread(client1);
        Thread t2 = new Thread(client2);
        t1.start();
        t2.start();
    }
}
