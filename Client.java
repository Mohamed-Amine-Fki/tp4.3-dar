package serveur;
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {

        try {
        	System.out.println("Je suis un client non conncté");
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
        	System.out.println("Je suis un client conncté");

            String request = "Demande de date ";
            byte[] sendData = request.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 1234);
            clientSocket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());

            System.out.println("Date et heure fournies par le serveur: " + response);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
