import java.io.*; 
import java.net.*; 
import java.util.*;

public class Client
{
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private double latitude;
    private double longitude;
    private int health;
    
    public Client(Socket s) throws IOException
    {
        socket = s;
		out = new PrintWriter(s.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		give("longitude");
		latitude = Double.parseDouble(get());
		give("latitude");
		longitude = Double.parseDouble(get());
		health = 100;
    }
    
    public void give(String str) throws IOException
    {
        out.println(str);
    }
    
    public String get() throws IOException
    {
        return in.readLine();
    }
    
    public void takeDamage(){
    	health -= 20;
    }
    
    public int getHealth(){
    	return health;
    }
    
    public double angleFinder(double targetX, double targetY ){
		double theta, angle;
		theta = Math.atan2(targetY , targetX);
		theta += Math.PI/360;
		angle = Math.toDegrees(theta);
		if (angle < 0) {
	        angle += 360;
	    }
		return angle;
	}
	
	public double targetDistance(double targetX, double targetY ){
		double distance;
		distance = Math.sqrt((targetX * targetX) + (targetY * targetY));
		return distance;
	}
	
	public double getLongitude(){
	    return longitude;
	}
	
	public double getLatitude(){
	    return latitude;
	}
    
}
