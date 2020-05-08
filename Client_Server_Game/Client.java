//importing the necessary packages
import java.io.*; 
import java.net.*; 


public class Client extends Thread
{ 
	//declaring and initializing variables 
	//variable num_client1 which initially guesses the value of 10
	int num_client1 = 10;
	//variable num_client2 which initially guesses the value of 100
	int num_client2 = 100;
	//variable num_client3 which initially guesses the value of 50
	int num_client3 = 50;
	//variable to store the value which comes from the server
	String resultFromServer;
	//variable result to store either -1 , 1 or 0
	int result = 2;
	//variable socket which establishes the connection to server
	Socket s;
	//variable data output stream which is used to send data over network to server
	DataOutputStream dos;
	//variable buffered reader to read data coming from server
	BufferedReader br;

	
	//main method
	    public static void main(String args[]) throws Exception 
	    { 
	    	//creating client object
	        Client c = new Client();
	        
	    	// Create client socket  which takes port number and ip address 
	        c.s = new Socket("localhost", 888); 
	  
	        // to send data to the server 
	        c.dos = new DataOutputStream( c.s.getOutputStream()); 
	  
	        // to read data coming from the server 
	        c.br  = new BufferedReader( new InputStreamReader( c.s.getInputStream())); 
	        
	        //creating thread object which acts as client1
	        Thread thread1 = new Thread(c) 
	        {
	        	//run method which is called internally every time whenever thread object is created
	        	public void run() {
	        		//method call to client1 object
	        	c.client1();
	        	}
	        };
	        
	      //creating thread object which acts as client2
	        Thread thread2 = new Thread(c) 
	        {
	        	//run method which is called internally every time whenever thread object is created
	        	public void run() {
	        		//method call to client2 object
	        	c.client2();
	        	}
	        };
	        Thread thread3 = new Thread(c) 
	        {
	        	//run method which is called internally every time whenever thread object is created 
	        	public void run() {
	        		//method call to client3 object
	        	c.client3();
	        	}
	        };
	        
	        //method call which starts threads concurrently
	        thread1.start();
	        thread2.start();
	        thread3.start();
	        
	    }
	
	    
	    //method to guess the stock price based on the output from the server
	public void newGuess1()
	{
		//if server sends 1 meaning that the guess is higher
		if(result == 1)
		{
			num_client1 = num_client1 - 10;
		}
		//if server sends -1 meaning that guess is less than the stock price
		if(result == -1)
		{
			num_client1 = num_client1 + 10;
		}
		//if result is 0 then the guess and stock price are the same
		if(result == 0)
		{
			System.out.println("yahoooo, i guessed first");
		}
	}
	public void newGuess2()
	{
		if(result == 1)
		{
			num_client2 = (num_client2 - 1);
		}
		if(result == -1)
		{
			num_client2 = (num_client2  + 1);
		}
		if(result == 0)
		{
			System.out.println("yahoooo, i guessed first");
		}
	}
	public void newGuess3()
	{
		if(result == 1)
		{
			num_client3 = (num_client3 - 5);
		}
		if(result == -1)
		{
			num_client3 = (num_client3 + 5);
		}
		if(result == 0)
		{
			System.out.println("yahoooo, i guessed first");
		}
	}
	
	//client1 method
	public void client1()
	{
		//try catch block to handle the exception
		try 
		{
			//the loop should iterate till the server send 0 to client
			while(result != 0)
	        {
				System.out.println("client1 connected");
				dos.writeBytes(Integer.toString(num_client1) + "\n");
	        	resultFromServer = br.readLine();
	        	result = Integer.parseInt(resultFromServer);
	        	//method call which either increases or decreases the guess number
	        	newGuess1();	
	        }
			//method calls to close all the opened connections
			  s.close();
	 	      dos.close();
	 	      br.close();
	 	      
		}catch(Exception e)
		{
			System.out.println("client1 lost");
		}
		
	}
	public void client2()
	{
		try 
		{
			while(result != 0)
	        {
				System.out.println("client2 connected");
				dos.writeBytes(Integer.toString(num_client2) + "\n");
	        	resultFromServer = br.readLine();
	        	result = Integer.parseInt(resultFromServer);
	        	newGuess2();	
	        }
			  s.close();
	 	      dos.close();
	 	      br.close();
		}catch(Exception e)
		{
			System.out.println("client2 lost");
		}
	}
	public void client3()
	{
		try 
		{
			while(result != 0)
	        {
				System.out.println("client3 connected");
				dos.writeBytes(Integer.toString(num_client3) + "\n");
	        	resultFromServer = br.readLine();
	        	result = Integer.parseInt(resultFromServer);
	        	newGuess3();	
	        }
			  s.close();
	 	      dos.close();
	 	      br.close();
		}catch(Exception e)
		{
			System.out.println("client3 lost");
		}
	}
	
} 
