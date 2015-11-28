public class Client
{
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private double latitude;
    private double longitude;
    
    public Client(Socket s) throws IOException
    {
        socket = s;
		out = new PrintWriter(s.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		//INITIALIZE variables to the longitude and latitude of the phone
		latitude = 0;
		longitude = 0;
    }
    
    public void give(String str) throws IOException
    {
        out.println(str);
    }
    
    public String get() throws IOException
    {
        return in.readLine();
    }
    
    public static double angleFinder(double targetX, double targetY ){
		double theta, angle;
		theta = Math.atan2(targetY , targetX);
		theta += Math.PI/360;
		angle = Math.toDegrees(theta);
		if (angle < 0) {
	        angle += 360;
	    }
		return angle;
	}
	
	public static double targetDistance(double targetX, double targetY ){
		double distance;
		distance = Math.sqrt((targetX * targetX) + (targetY * targetY));
		return distance;
	}
	
	public double getX(){
	    return x;
	}
	
	public double getY(){
	    return y;
	}
    
    
    
}
