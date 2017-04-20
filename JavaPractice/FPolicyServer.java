import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.ByteBuffer;

public class FPolicyServer {

    private static final int LEN = 6;

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

            StringBuilder reqStr = new StringBuilder();
            byte[] buffer = new byte[LEN];
            int temp = LEN;
            while (temp > 0) {
                int numBytes = connectionSocket.getInputStream().read(buffer);
                reqStr.append(new String(buffer, LEN - temp, numBytes, StandardCharsets.UTF_8));
                temp -= numBytes;
            }
            System.out.println("PRAGAD . B: " + reqStr);
            capitalizedSentence = reqStr.toString().toUpperCase() + '\n';
            byte[] finalResp = capitalizedSentence.getBytes(StandardCharsets.UTF_8);
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            outToClient.write(finalResp, 0, finalResp.length);

            /*
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            System.out.println("PRAGAD . B: " + inFromClient);
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            System.out.println("PRAGAD . C: " + outToClient);
            clientSentence = inFromClient.readLine();
            System.out.println("PRAGAD . D: Received: " + clientSentence);
            capitalizedSentence = clientSentence.toUpperCase() + '\n';
            outToClient.writeBytes(capitalizedSentence);
            outToClient.flush();
            */
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
