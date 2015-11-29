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
    	(new Client(server.accept())).give("victim");
    	System.out.println("Victim found.");
    	Client victim = new Client(server.accept());
    	System.out.println("Victim connected.");
    	(new Client(server.accept())).give("assassin");
    	System.out.println("Assassin found.");
    	Client assassin = new Client(server.accept());
    	System.out.println("Assassin connected.");
    	
    	while (true)
    	{
    		victim.give("ping");
    		if (victim.get() == null) {
    			System.out.println("Victim disconnected.");
    			break;
    		}
    		
    		assassin.give("ping");
    		if (assassin.get() == null) {
    			System.out.println("Assassin disconnected.");
    			break;
    		}
    		
    		victim.give("latitude");
			double vicLat = Double.parseDouble(victim.get());
			victim.give("longitude");
			double vicLng = Double.parseDouble(victim.get());
    		assassin.give("latitude");
			double assLat = Double.parseDouble(assassin.get());
			assassin.give("longitude");
			double assLng = Double.parseDouble(assassin.get());
			
    		double assAngle = assassin.angleFinder(vicLat, vicLng);
    		assassin.give("angle " + assAngle);
			
			if (distFrom(vicLat, vicLng, assLat, assLng) < 2)
				victim.give("hurt");
			
			victim.give("health");
			if (Integer.parseInt(victim.get()) == 0)
				break;
		}
		
		System.out.println("Assassin wins!");
    }
    
	public static float distFrom(float lat1, float lng1, float lat2, float lng2) {
	    double earthRadius = 6371000; //meters
	    double dLat = Math.toRadians(lat2-lat1);
	    double dLng = Math.toRadians(lng2-lng1);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    float dist = (float) (earthRadius * c);
	
	    return dist;
    }

}
