import java.io.*;
import java.net.*;
import java.util.*;

public class Server
{
	private ServerSocket server;
	
	public Server() throws IOException
	{
		try {
			server = new ServerSocket(8080);
			listen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public static void main(String[] args) throws IOException       
    {          
        new Server();
    }
    
    public void listen() throws IOException
    {
    	Client assassin;
    	Client victim;
    	double assAngle, vicAngle, distance;
    	
    	victim = new Client(server.accept());
    	System.out.println("Victim connected.");
    	assassin = new Client(server.accept());
    	System.out.println("Assassin connected.");
    	
    	while (true)
    	{
    		
    		
    		victim.give("ping");
    		assassin.give("ping");
    		
    		//debug statements
    		System.out.println(victim.get());
    		System.out.println(assassin.get());
    		
    		if(victim.get() == null){
    			System.out.println("Victim disconnected.");
    			break;
    		}
    		
    		if(assassin.get() == null){
    			System.out.println("Assassin disconnected.");
    			break;
    		}
			
    		assAngle = assassin.angleFinder(victim.getLatitude(),victim.getLongitude());
    		vicAngle = victim.angleFinder(assassin.getLatitude(),assassin.getLongitude());
    		distance = assassin.targetDistance(victim.getLatitude() - assassin.getLatitude(), victim.getLongitude()-assassin.getLongitude());
    		
    		if(victim.getHealth() <= 0){
    			System.out.println("Assassin has slain the victim. Well played.");
 				assassin.give("Assassin Wins!");
 				break;
    		}
    		
 			if(distance <= 1) {
 				victim.takeDamage();
 				victim.give("You are being attacked. Your Health: " + victim.getHealth());
 				//1.5 second "recovery" time
 				try {
    				Thread.sleep(1500);
				} catch(InterruptedException ex) {
    				Thread.currentThread().interrupt();
				}
 			}
		}
    }
    

	
}
