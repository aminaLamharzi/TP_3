

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class FileReceiver {
    public static void main(String[] args) throws IOException {
        MulticastSocket multicastSocket = new MulticastSocket(12345);
        InetAddress groupAddress = InetAddress.getByName("224.0.0.1");
        multicastSocket.joinGroup(groupAddress);
        
        FileOutputStream fileOutputStream = new FileOutputStream("rec.pptx");
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        while (true) {
            multicastSocket.receive(packet);
            fileOutputStream.write(packet.getData(), 0, packet.getLength());
            if (packet.getLength() < buffer.length) {
                break;
            }
        }

        fileOutputStream.close();
        multicastSocket.leaveGroup(groupAddress);
        multicastSocket.close();
        System.out.println("Fichier reçu avec succès.");
    }
}
