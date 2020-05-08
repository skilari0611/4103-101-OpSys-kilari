//importing the necessary packages
import java.io.*; 
import java.net.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
	
public class Server 
{
	//variable stock prize to store the value 
	 static int stockPrice = 500;
	 //variable guess to store the value which the client guesses
	 static int guess;
	 //variable string to store the data coming from the client
	 static String str;
	 //variable result to store the value of either -1, +1 or 0
	 static int result = 2;
	 public static void main(String args[]) throws Exception 
	    { 
		 
		//stockPrice = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE + 1);
		 
		 
		 //semaphore object which is used to implement the mutex concept by allowing only
		 //specified threads to the code, if it is 1 it will processes only 1 thread
		 //if it is other number it can prcoess that many threads
		 Semaphore mutex = new Semaphore(1);
		 	 
		 
		 // Create server Socket 
		 ServerSocket ss = new ServerSocket(888); 
		// connect it to client socket 
		 Socket socket = ss.accept();
	        
	        
	        System.out.println("Connection established"); 
	  
	        // to send data to the client 
	        OutputStream output = socket.getOutputStream();
	        PrintWriter writer = new PrintWriter(output, true);
	  
	        // to read data coming from the client 
	        BufferedReader br = new BufferedReader( new InputStreamReader( socket.getInputStream())); 
	        while(result != 0)
	        {
	        	//method call where the mutex concept starts,which process only 1 thread 
	        	mutex.acquire();
	        	
	        	//readline method to read data from the client
	        	str = br.readLine();
	        	
	        	//storing the value to the variable
	        	guess = Integer.parseInt(str);
		       // System.out.println("client connected: " +  );
		        System.out.println("Client guesses :" + guess);
		        //method call to check if the client guesses more than the stockprice  or less
		        stockCheck();
		        //method call to write the result of the server to the client
		        writer.println(result);
		        //method call which releases the mutex
		        mutex.release();
	        }
	        
	        //method calls to close the connections
	        ss.close();
	        writer.close();
	        br.close();
	 } 
	 
	 //method to compare the stock price with the client guess
	 public static void stockCheck()
	 {
		 //if guess is same as the stock price then server should respond 0
		 if(guess == stockPrice)
		 {
			 result = 0;
		 }
		 //if guess is more than the stock price then server should respond 1
		 if(guess > stockPrice)
		 {
			 result = 1;
		 }
		 //if guess is less than the stock price then server should respond -1
		 if(guess < stockPrice)
		 {
			 result = -1;
		 }
		 
	 }
}
