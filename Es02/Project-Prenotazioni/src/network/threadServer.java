package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class threadServer { 
	ReservationManager reservations;
	
	
	public static void main(String[] args) throws Exception {
	   
		int severPort=8698;
		int clienCount = 0;       // conta il numero di client
		int reservationsSize=10;  // numero di posti
		ReservationManager reservations= new ReservationManager(reservationsSize);
		
		// Listen to port
		ServerSocket sock = new ServerSocket(8698);
		System.out.println("Server: in ascolto sula porta " + severPort );
			
		while (true) {
			// Start accepting requests and wait until client connects
			Socket serverClientSocket = sock.accept();  // bloccante
			clienCount++;
			System.out.println("Server: Serving Client " + clienCount);
			// Handle the client communication
			WorkerThread sa = new WorkerThread( "Thread-Numero-" +clienCount ,  serverClientSocket, clienCount , reservations);
			//sa.setName("Thread-Numero-" +count);  
			sa.start();  // non è bloccante
		}
			
	}
}
