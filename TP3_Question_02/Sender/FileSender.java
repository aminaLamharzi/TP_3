

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class FileSender {
    public static void main(String[] args) throws IOException {
        String filePath = "file.pptx";
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        MulticastSocket multicastSocket = new MulticastSocket();
        byte[] buffer = new byte[1024];
        int bytesRead;
        InetAddress groupAddress = InetAddress.getByName("224.0.0.1");

        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            DatagramPacket packet = new DatagramPacket(buffer, bytesRead, groupAddress, 12345);
            multicastSocket.send(packet);
        }

        fileInputStream.close();
        multicastSocket.close();
        System.out.println("Fichier envoyé avec succès.");
    }
}
