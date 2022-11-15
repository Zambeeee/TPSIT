package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
	public static void main(String[] args) throws Exception {
		try {
			int countEl = 0;
			int vocaliEl = 0;
		    int consonantiEl = 0;
			int vocali;
			int consonanti;
			
			// Listen to port
			ServerSocket server = new ServerSocket(8698);
			System.out.println("Apertura del socket e attesa connessioni");
			Socket serverClientSocket = server.accept();
			DataInputStream inStream = new DataInputStream(serverClientSocket.getInputStream());
			DataOutputStream outStream = new DataOutputStream(serverClientSocket.getOutputStream());
			
			String clientMessage = "";
			while(!clientMessage.equals("Quit")) {
				vocali = 0;
				consonanti = 0;
		        clientMessage = inStream.readUTF();
		        for(int i = 0; i < clientMessage.length(); i++) {	 
		    	    if(Character.isLetter(clientMessage.charAt(i))) {
                        countEl++;
                    }
			    }
		        for(int i = 0; i < clientMessage.length(); ++i) {
		            char ch = clientMessage.charAt(i);
		            if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
		                vocaliEl++;
						vocali++;
                    }
		        }
                if(countEl!=vocali) {           //Da Sistemare
                    consonanti=countEl-vocali;
					consonantiEl+=consonanti;
                }
			    outStream.writeUTF("Server: " + clientMessage + "\nLettere: " + countEl + "\nnumero consonanti : " + consonantiEl + "\nnumero vocali :" + vocaliEl);
			    countEl = 0;
    			outStream.flush();             
			    if(consonantiEl==(vocaliEl/2)) {
	        	    clientMessage="Quit";
                }
			}
		}
		catch (Exception e) {
		    System.out.println(e);
		}
	}
}