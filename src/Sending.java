import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;

public class Sending implements Runnable {
    private User user;
    private Lock lock;

    public Sending(User user) {
        this.user = user;
    }

    @Override
    public void run() {
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
}
