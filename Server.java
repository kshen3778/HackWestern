import java.io.*; 
import java.net.*; 

class Server {    
    public static void main(String args[]) throws Exception       
    {          
        
        try {
			ServerSocket server = new ServerSocket(8080);
			while (true) {
				Socket client = server.accept();
				System.out.println(client);
				PrintWriter out = new PrintWriter(client.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				out.println("exit");
				System.out.println("A");
				System.out.println(in.readLine());
			}
			//server.close();'/'
			//System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
    } 
    
    //public static double angleFinder(double targetX, double targetY ){
	//	double theta, angle;
	//	theta = Math.atan2(targetY , targetX);
	//	theta += Math.PI/1360;
	//	angle = Math.toDegrees(theta);
	//	if (angle < 0) {
	//        angle += 360;
	//    }
	//	return angle;
	//}
	
	//public static double targetDistance(double targetX, double targetY ){
	//	double distance;
	//	distance = Math.sqrt((targetX * targetX) + (targetY * targetY));
	//	return distance;
	//}
	
	//	public static int[][] gameData(int playerCount){
	//	ArrayList<Integer> randomizer = new ArrayList<Integer>();
	//	int[][] data = new int[playerCount][2];
	//	for(int i = 0; i < playerCount; i++){
	//		randomizer.add(new Integer(i+1));
	//		Collections.shuffle(randomizer);
	//	}
	//	
	//	for(int i = 0; i < playerCount; i++){
	//		data[i][0] = i+1;
	//		data[i][1] = randomizer.get(i);
	//	}
	//	
	//	System.out.println(Arrays.deepToString(data));
	//	return data;
	//}
	
}
