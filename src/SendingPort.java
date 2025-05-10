import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class SendingPort extends Thread {
    private User user;

    public SendingPort(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try (DatagramSocket socket = new DatagramSocket();
             Scanner in = new Scanner(System.in)) {

            while (true) {
                System.out.print("输入你要发送的消息(输入exit退出)");
                String message = in.nextLine();
                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }

                byte[] buf = (user.getName() + ": " + message).getBytes();
                InetAddress address = InetAddress.getByName("192.168.1.111"); // 改为实际IP
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 8888);
                socket.send(packet);
            }
        } catch (Exception e) {
            System.err.println("发送消息出错: " + e.getMessage());
        }

    }

    public static void main(String[] args) throws Exception {
        User user1 = new User("猴哥");
        User user2 = new User("八戒");
        SendingPort client1 = new SendingPort(user1);
        SendingPort client2 = new SendingPort(user2);
        client1.start();
        client2.start();
    }
}
