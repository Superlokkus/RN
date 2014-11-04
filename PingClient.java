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
	      
	      Duration RTTsum = Duration.ZERO; Duration RTTmin = Duration.ZERO; Duration RTTmax = Duration.ZERO;
	      int i = 0;
	      for (; i < 10; i++)
	      {
	    	  String msg = msg_template;
	    	  msg = msg.replace("SEQ", Integer.toString(i));
	    	  
	    	  Instant presend = Instant.now();
	    	  msg = msg.replace("TS", (presend.toString()));
	    	  
	    	  sendData = msg.getBytes();         
		      DatagramPacket sendPacket = 
		         new DatagramPacket(sendData, sendData.length, IPAddress, Integer.parseInt(args[1])); 
		  
		      
		      clientSocket.send(sendPacket);   
		  
		      DatagramPacket receivePacket = 
		         new DatagramPacket(receiveData, receiveData.length); 
		
		      clientSocket.receive(receivePacket);
		      Instant postsend = Instant.now();
		  
		      
		      String modifiedSentence = 
		          new String(receivePacket.getData()); 
		      
		      Duration RTT = Duration.between(presend,postsend);
		  
		      System.out.println("FROM SERVER:" + modifiedSentence + "RTT: "
		      + RTT.toMillis() + " ms"); 
		       
		      RTTsum = RTTsum.plus(RTT);
		      
		      if (RTT.compareTo(RTTmax) > 0)
		      {
		    	  RTTmax = RTT;
		      }
		      else if (RTT.compareTo(RTTmin) < 0)
		      {
		    	  RTTmin = RTT;
		      }
		      
	      }
	      System.out.println("Max RTT: " + RTTmax.toMillis() + " ms. Min RTT: " + RTTmin.toMillis() +
	    		  " ms. Avg RTT: " + RTTsum.dividedBy(i+1).toMillis() + " ms.");
	      
	      clientSocket.close();
	}
		static String msg_template = "PING SEQ TS\r\n";
}