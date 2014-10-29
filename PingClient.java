import java.io.*; 
import java.net.*;


class PingClient
{
	public static void main(String args[]) throws Exception 
	{
		BufferedReader inFromUser = 
	        new BufferedReader(new InputStreamReader(System.in)); 
	  
	      DatagramSocket clientSocket = new DatagramSocket(); 
	      clientSocket.setSoTimeout(5 * 1000);
	      System.out.println(clientSocket.getReceiveBufferSize());

	      InetAddress IPAddress = InetAddress.getByName(args[0]); 
	  
	      byte[] sendData = new byte[1024]; 
	      byte[] receiveData = new byte[1024]; 
	      
	      long start = System.nanoTime();
	      sendData = msg.getBytes();         
	      DatagramPacket sendPacket = 
	         new DatagramPacket(sendData, sendData.length, IPAddress, Integer.parseInt(args[1])); 
	  
	      clientSocket.send(sendPacket);   
	  
	      DatagramPacket receivePacket = 
	         new DatagramPacket(receiveData, receiveData.length); 
	
	      clientSocket.receive(receivePacket);
	      long duration = System.nanoTime() - start;
	  
	      String modifiedSentence = 
	          new String(receivePacket.getData()); 
	  
	      System.out.println("FROM SERVER:" + modifiedSentence + " RTT: " + duration / 10e6 + " ms"); 
	      clientSocket.close(); 
		
		System.out.println(msg);
	}
		static String msg = "PING\r\n";
}