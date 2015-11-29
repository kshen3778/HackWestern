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
    private int damage;
    
    public Client(Socket s) throws IOException
    {
        socket = s;
		out = new PrintWriter(s.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		System.out.println("client...");
		give("latitude");
		latitude = Double.parseDouble(get());
		System.out.println("latitude: " + latitude);
		give("longitude");
		longitude = Double.parseDouble(get());
		System.out.println("longitude: " + longitude);
		health = 100;
		damage = 20;
    }
    
    public Client(Socket s, boolean b) throws IOException
    {
    	if (b) {
	        socket = s;
			out = new PrintWriter(s.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			give("latitude");
			latitude = Double.parseDouble(get());
			give("longitude");
			longitude = Double.parseDouble(get());
			health = 100;
			damage = 20;
    	} else {
	        socket = s;
			out = new PrintWriter(s.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    	}
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
    	health -= damage;
    }
    
    public void subtractDamage(int n){
    	damage -= n;
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
    
    public int heatTone(double lat, double lon ){
		double distance = 0;
		int heat = 0;
		distance = Math.sqrt(((lat - latitude) * (lat - latitude)) + ((lon - longitude) * (lon - longitude)));
		if(distance <= 1){
			heat = 5;
		}else if (distance <= 2){
			heat = 4;
		}else if (distance <= 4){
			heat = 3;
		}else if (distance <= 8){
			heat = 2;
		}else if (distance <= 16){
			heat = 1;
		}else{
			heat = 0;
		}
		return heat;
	}
	
	public boolean isStabbing() throws IOException{
		return get().equals("true");
	}
	
}
