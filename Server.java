import java.io.*; 
import java.net.*; 
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Server
{    
	ServerSocket server;
	
    public static void main(String[] args) throws Exception       
    {          
        try {
			server = new ServerSocket(8080);
			listen();
		} catch (Exception e) {
			e.printStackTrace();
		}
    } 
    
    public void listen() throws IOException
    {
    	Client assassin;
    	Client victim;
    	
    	victim = new Client(server.accept());
    	System.out.println("Victim connected.");
    	assasin = new Client(server.accept());
    	System.out.println("Assassin connected.");
    	
    	while (true)
    	{
    		
		}
    }
    
	
}
