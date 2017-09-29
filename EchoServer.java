
import java.net.ServerSocket;
import java.net.Socket;

public final class EchoServer {

	public static void main(String[] args) throws Exception {
		try (ServerSocket serverSocket = new ServerSocket(22222)) {
			while (true) {
				Socket socket = serverSocket.accept();
				Thread t = new Thread( new ServerRunnable(socket));
				t.start();
			}
		}
	}
}
