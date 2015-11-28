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
    		 assAngle = assassin.angleFinder(victim.getLatitude(),victim.getLongitude());
    		 vicAngle = victim.angleFinder(assassin.getLatitude(),assassin.getLongitude());
    		 distance = assassin.targetDistance(victim.getLatitude() - assassin.getLatitude(), victim.getLongitude()-assassin.getLongitude());
 			if(distance <= 100){
 				System.out.println("Assassin has slain the victim. Well played.");
 				assassin.give("Assassin Wins!");
 				break;
 			}
		}
    }
    
	
	//	public static void dataUpdate(int player, int dead){
	//		data[player-1][1] = data[dead-1][1];
	//}
	
}
