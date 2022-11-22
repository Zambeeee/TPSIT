package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

class TcpServer extends Thread {

	Socket serverSocket;
	int clientCount;

	TcpServer(Socket socketS, int clientC) {
		serverSocket = socketS;
		clientCount = clientC;
	}

	public void run() {

		try {

			// Streams to read and write the data to socket streams
			DataInputStream inStream = new DataInputStream(serverSocket.getInputStream());
			DataOutputStream outStream = new DataOutputStream(serverSocket.getOutputStream());
			String clientMsg = "";
			String serverMsg = "";

			while (!clientMsg.equals("end")) {

				//Leggiamo il messaggio proveniente dal client, e lo stampiamo a schermo
				clientMsg = inStream.readUTF();
				System.out.println("Server.Thread " + clientCount + " Ricevuto messaggio " + clientMsg );

				//Echo
				serverMsg=clientMsg;
				System.out.println("Server.Thread " + clientCount + " Invio messaggio " + serverMsg );
				outStream.writeUTF(serverMsg);
				outStream.flush();
			}
			
			//Prima di chiudere la connessione viene inviato un ultimo messaggio
			serverMsg="Bye";
			System.out.println("Server.Thread " + clientCount + " Invio messaggio " + clientMsg );
			outStream.writeUTF(serverMsg);
			outStream.flush();

			//Chiusura delle risorse utilizzate
			inStream.close();
			outStream.close();
			serverSocket.close();

		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			System.out.println("Client -" + clientCount + " exit!! ");
		}
	}
}