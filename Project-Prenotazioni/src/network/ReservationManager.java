package network;

public class ReservationManager {
   
    private String[] reservations;

    //Costruttore
    ReservationManager(int reservationsSize ) {
    	reservations=new String[reservationsSize];
    	for (int i=0 ; i<reservationsSize ; i++ ) {
    		reservations[i]="Posto_Libero";
    	}
    }
     
    public synchronized String getReservations() {
    	String res="";
    	for(int i=0 ; i<reservations.length ; i++ ) {
    		res= res + "\n" +  i + " " + reservations[i];
    	}
        return res;
    }
    
    public synchronized String setReservation( int seatNum, String name ) {
    	String result = "Riservato per " + name + " Rifiutato";
    	if(reservations[seatNum].equals("Posto_Libero")) {
    		reservations[seatNum]=name;
    		result="Riservato per " + name + " Accettato";
    	} 
        return result;
    }
 
}
