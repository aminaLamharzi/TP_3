package tp3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Emetteur {
    public static void main(String[] args) throws IOException {
        String filePath = "file.pptx";
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        DatagramSocket datagramSocket = new DatagramSocket();
        byte[] buffer = new byte[1024];
        int bytesRead;
        InetAddress receiverAddress = InetAddress.getLocalHost();

        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            DatagramPacket packet = new DatagramPacket(buffer, bytesRead, receiverAddress, 12345);
            datagramSocket.send(packet);
        }

        fileInputStream.close();
        datagramSocket.close();
        System.out.println("Fichier envoyé avec succès.");
    }
}