package network;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.DataInputStream;

public class MultithreadTpc {

	static final int MAX_CONN = 999;
	static final int SRV_PORT = 8698;

	public static void main(String[] args) throws Exception {
	   
		int count = 0;	// count
		String stringa;
		// Creazione del socket
		ServerSocket server = new ServerSocket(SRV_PORT);
		
		
		while(count<MAX_CONN) {
			count++;
			System.out.println("Server: in ascolto sulla porta " + SRV_PORT );
			Socket serverClientSocket = server.accept();  // bloccante
			
			DataInputStream inStream = new DataInputStream(serverClientSocket.getInputStream());
			DataOutputStream outStream = new DataOutputStream(serverClientSocket.getOutputStream());
			
			System.out.println("Serving Client " + count);
			TcpServer sa = new TcpServer(serverClientSocket, count);
			
			System.out.println("Client: invio il messaggio: " + count);
			stringa = ""+count;
			outStream.writeUTF(stringa);
			outStream.flush();
		}
		server.close();
	}
}