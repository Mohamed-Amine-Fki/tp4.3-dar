package serveur;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.text.SimpleDateFormat;




public class Server extends Thread{
	int ord;
	public static void main(String[] args) {
		new Server().start();
		
	}
	public void run() {
		try {

			System.out.println("Démarrage du Server");
			DatagramSocket serverSocket = new DatagramSocket(1234);
			while(true) {
				new ClientProcess(serverSocket, ++ord).start();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public class ClientProcess extends Thread {
		DatagramSocket socket;
		private int numClient;
		public ClientProcess(DatagramSocket serverSocket,int numClient) {
			super();
			this.socket = serverSocket;
			this.numClient = numClient; 
		}
		public void run() {
			try {


	            byte[] receiveData = new byte[1024];
				          
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = dateFormat.format(new Date());

                byte[] sendData = currentTime.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                socket.send(sendPacket);

				socket.close();
			} catch (IOException  e) {
				e.printStackTrace();
			}
		}
	}
	
}