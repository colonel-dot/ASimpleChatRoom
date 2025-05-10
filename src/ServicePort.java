import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class ServicePort {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(8888);
        int port = 8888;
        Scanner in = new Scanner(System.in);
        byte[] bytes = new byte[1024];
        System.out.println("Welcome to the chat room!");
        while (true) {
            DatagramPacket packet = new DatagramPacket(bytes, 1024);
            socket.receive(packet);
            bytes = packet.getData();
            int len = packet.getLength();
            String message = new String(bytes, 0, len);
            System.out.println(message);
        }
    }
}
