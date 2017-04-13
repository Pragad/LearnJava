import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class FPolicyServer {

    // Create a listener that listens on an all observer IPs
    // That is the listener that observer will be calling with all observer IPs

    // Use the IP of the observer
    // Make sure you get the request from netapp
    public static void createFPolicyListener(ServerSocket serverSocket) throws Exception {

        String clientSentence;
        String capitalizedSentence;
        @SuppressWarnings("resource")
        ServerSocket welcomeSocket = new ServerSocket(6789);

        while (true) {
            @SuppressWarnings("resource")
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("PRAGAD . A: " + connectionSocket);
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            System.out.println("PRAGAD . B: " + inFromClient);
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            System.out.println("PRAGAD . C: " + outToClient);
            clientSentence = inFromClient.readLine();
            System.out.println("PRAGAD . D: Received: " + clientSentence);
            capitalizedSentence = clientSentence.toUpperCase() + '\n';
            outToClient.writeBytes(capitalizedSentence);
        }

    }

    public static void main(String[] args) throws Exception {
        System.out.println("FPolicy Handler");
        /*
        InetAddress addr = InetAddress.getByName("10.1.155.49");
        ServerSocket socket = new ServerSocket(1234, 50, addr);
        FPolicyServer fPolicyServer = new FPolicyServer();
        fPolicyServer.createFPolicyListener(socket);
        */

        FPolicyServer.createFPolicyListener(null);
    }
}
