import java.io.*; 
import java.net.*;
import java.time.*;

class PingClient
{
	public static void main(String args[]) throws Exception 
	{
		BufferedReader inFromUser = 
	        new BufferedReader(new InputStreamReader(System.in)); 
	  
	      DatagramSocket clientSocket = new DatagramSocket(); 
	      clientSocket.setSoTimeout(1 * 1000);
	      

	      InetAddress IPAddress = InetAddress.getByName(args[0]); 
	  
	      byte[] sendData = new byte[1024]; 
	      byte[] receiveData = new byte[1024]; 
	      
	      for (int i = 0; i < 10; i++)
	      {
	    	  String msg = msg_template;
	    	  msg = msg.replace("SEQ", Integer.toString(i));
	    	  msg = msg.replace("TS", (Instant.now().toString()));

	    	  sendData = msg.getBytes();         
		      DatagramPacket sendPacket = 
		         new DatagramPacket(sendData, sendData.length, IPAddress, Integer.parseInt(args[1])); 
		  
		      clientSocket.send(sendPacket);   
		  
		      DatagramPacket receivePacket = 
		         new DatagramPacket(receiveData, receiveData.length); 
		
		      clientSocket.receive(receivePacket);
		      
		  
		      String modifiedSentence = 
		          new String(receivePacket.getData()); 
		  
		      System.out.println("FROM SERVER:" + modifiedSentence + "RTT: "); 
		       
	    	  
	      }
	      clientSocket.close();
	}
		static String msg_template = "PING SEQ TS\r\n";
}