public class Client
{
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    
    public Client(Socket s, PrintWriter pw, BufferedReader br)
    {
        socket = s;
        out = pw;
        in = br;
    }
    
    public void give(String str)
    {
        out.println(str);
    }
    
    public String get()
    {
        return in.readLine();
    }
    
}
