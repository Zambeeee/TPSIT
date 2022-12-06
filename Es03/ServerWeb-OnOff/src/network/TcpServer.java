/**
 * Implementazione di un server web utilizzando la comunicazione tramite socket.
 * Lettura dati multi riga provenienti dal client
 * 
 * from folder network/..
 * cd Es03/ServerWeb/src/network
 * java TcpServer.java
 */
package network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
	public static void main(String[] args) throws Exception {
		
		final int SERVER_PORT=8765;
		String clientMsg = "";
		try {			 
			// Creazione del socket sul server e ascolto sulla porta
			ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
			System.out.println("Server: in ascolto sulla porta " + SERVER_PORT);

			boolean endConn=false;
			while(!endConn) {
				// Attesa della connessione con il client
				System.out.println("Attesa ricezione dati dal client ....................... \n");
				Socket clientSocket = serverSocket.accept();
				
				// Create output stream to write data and input stream to read data from socket
				DataOutputStream outStream = new DataOutputStream(clientSocket.getOutputStream());	
				BufferedReader inStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	
				// ---------------------------------------------------------
				//Lettura dati dal client un righa alla volta   
				while ((clientMsg=inStream.readLine()).length() != 0) {
					System.out.println(clientMsg);	
				}  
				// Elaborare qui i dati ricevuti dal client 
				// ---------------------------------------------------------

				//Invio dei dati su stream di rete al client
				clientMsg = "HTTP/1.1 200 OK\r\n";
				//clientMsg += "Connection: close\r\n";
				clientMsg += "Content-Type: text/plain\r\n";
				clientMsg += "\r\n";
				
				
				String parola = "";
				String myArray[] = clientMsg.split(" ");
				for(int i=0; i<100; i++) {
					if(myArray[i] == "on") {
						parola = "accendo";
					}
				}
				if(parola == "accendo"){
					clientMsg += "Accendo le luci";
				}
				
				

				outStream.write(clientMsg.getBytes());
				outStream.flush();

				System.out.println("\n....................... Fine ricezione dati\n");
				// Close resources
				clientSocket.close();
				inStream.close();
				outStream.close();
			}

			// Close resources
			serverSocket.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}