
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public final class EchoClient {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		try (Socket socket = new Socket("localhost", 22222)) {

			Scanner s = new Scanner(System.in);
			
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			OutputStream os = socket.getOutputStream();
			PrintStream out = new PrintStream(os, true, "UTF-8");

			String a;
			do {
				System.out.print("Client> ");
				a = s.nextLine();
				out.println(a);
				System.out.print("Server> ");
				System.out.println(br.readLine());
			}
			while(!a.equals("exit"));
		}
	}
}