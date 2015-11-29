import java.io.*; 
import java.net.*; 
import java.util.*;

public class Client
{
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    
    public Client(Socket s) throws IOException
    {
        socket = s;
		out = new PrintWriter(s.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }
    
    public void give(String str) throws IOException
    {
        out.println(str);
    }
    
    public String get() throws IOException
    {
        return in.readLine();
    }
    
    public double angleFinder(double targetX, double targetY) {
		double theta, angle;
		theta = Math.atan2(targetY , targetX);
		theta += Math.PI/360;
		angle = Math.toDegrees(theta);
		if (angle < 0) {
	        angle += 360;
	    }
		return angle;
	}
	
}
