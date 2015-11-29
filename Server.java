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
    	double assAngle, vicAngle, distance, heatTone;
    	
    	(new Client(server.accept())).give("victim");
    	victim = new Client(server.accept());
    	System.out.println("Victim connected.");
    	(new Client(server.accept())).give("assassin");
    	assassin = new Client(server.accept());
    	System.out.println("Assassin connected.");
    	
    	//public static int[][] data;
    	//public void gameData(int playerCount){
		//	data = new int[playerCount][2];
		//	for(int i = 0; i < playerCount; i++){
		//		data[i][0] = i+1;
		//		data[i][1] = i;
		//	}
		//	data[0][1] = playerCount;
		//}
	
    	
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
    		heatTone = assassin.heatTone(victim.getLatitude(), victim.getLongitude()); 
    		
    		
    		assassin.give("assAngle" + assAngle);
    		//victim.give("vicAngle" + vicAngle);
    		assassin.give("distance" + distance);
    		assassin.give("heatTone" + heatTone);
    		
    		
    		
    		if(victim.getHealth() <= 0){
    			System.out.println("Assassin has slain the victim. Well played.");
 				assassin.give("Assassin Wins!");
 				break;
    		}
    		
 			if(distance <= 1 && assassin.isStabbing()) {
	 			victim.takeDamage();
	 			victim.give("Health: " + victim.getHealth());
	 			//1 second "recovery" time
	 			try {
	    			Thread.sleep(1000);
				} catch(InterruptedException ex) {
	    			Thread.currentThread().interrupt();
				}
 			}else if(distance > 1 && assassin.isStabbing()){
 				assassin.subtractDamage(2);
 			}
		}
    }
    

	
}
