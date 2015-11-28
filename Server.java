import java.io.*; 
import java.net.*; 
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

class Server
{    
	ServerSocket server;
	
    public static void main(String args[]) throws Exception       
    {          
        try {
			server = new ServerSocket(8080);

		} catch (Exception e) {
			e.printStackTrace();
		}
    } 
    
    public void listen()
    {
    	ArrayList<Socket> clients = new ArrayList<Socket>();
    	
    	ActionListener actionListener = new ActionListener(){
    		//After 30 seconds wait time
    		Socket client = server.accept();
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			System.out.println(client);
    	};
    	
    	Timer timer = new Timer(30000, actionListener);
    	timer.start();
    	
    	/*int time = 0;
    	while (time < max) {
			Socket client = server.accept();
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			System.out.println(client);
    	}*/
    	
    	while (true) {
    		
		}
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
	
	public static int[][] data;
	
	public static void gameData(int playerCount){
		ArrayList<Integer> randomizer = new ArrayList<Integer>();
		int[][] data = new int[playerCount][2];
		for(int i = 0; i < playerCount; i++){
			randomizer.add(new Integer(i+1));
		}
	
		Collections.shuffle(randomizer);
		
		for(int i = 0; i < playerCount; i++){
			data[i][0] = i+1;
			data[i][1] = randomizer.get(i);
		}
		
	}
	
}
