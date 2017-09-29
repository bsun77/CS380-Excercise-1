
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public final class EchoServer {

	public static void main(String[] args) throws Exception {
		try (ServerSocket serverSocket = new ServerSocket(22222)) {
			while (true) {
				try (Socket socket = serverSocket.accept()) {
					String address = socket.getInetAddress().getHostAddress();
					System.out.printf("Client connected: %s%n", address);

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

					System.out.printf("Client disconnected: %s%n", address);
				} 
			}
		}
	}
}
