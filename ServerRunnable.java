import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;


public class ServerRunnable implements Runnable {
	
	private Socket socket;
	
	public ServerRunnable(Socket socket) {
		this.socket = socket;
//		System.out.println(this.socket.isClosed());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String address = socket.getInetAddress().getHostAddress();
		System.out.printf("Client connected: %s%n", address);
		try{
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		OutputStream os = socket.getOutputStream();
		PrintStream out = new PrintStream(os, true, "UTF-8");

		String b;
		do{
			b = br.readLine();
			out.println(b);
		} while (!b.equals("exit"));
		} catch ( Exception e ){
			System.out.println("Error.");
			e.printStackTrace();
		}
		System.out.printf("Client disconnected: %s%n", address);		
	}

}
