package tp3;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Recepteur {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(12345);
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        FileOutputStream fileOutputStream = new FileOutputStream("rec.pptx");

        while (true) {
            datagramSocket.receive(packet);
            fileOutputStream.write(buffer, 0, packet.getLength());
            if (packet.getLength() < buffer.length) {
                break;
            }
        }

        fileOutputStream.close();
        datagramSocket.close();
        System.out.println("Fichier reçu avec succès.");
    }
}

