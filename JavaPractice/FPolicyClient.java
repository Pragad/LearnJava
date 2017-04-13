import java.io.*;
import java.net.*;

class FPolicyClient
{
    public static void main(String argv[]) throws Exception
    {
        String sentence;
        String modifiedSentence;
        System.out.println("1: ");
        BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
        System.out.println("2: " + inFromUser);
        Socket clientSocket = new Socket("10.1.10.68", 6789);
        System.out.println("3: " + clientSocket);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        System.out.println("4: " + outToServer);
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("5: Enter some text: " + inFromServer);
        sentence = inFromUser.readLine();
        System.out.println("6: " + sentence);
        outToServer.writeBytes(sentence + '\n');
        System.out.println("7: ");
        modifiedSentence = inFromServer.readLine();
        System.out.println("FROM SERVER: " + modifiedSentence);
        clientSocket.close();
        System.out.println("10: ");
    }
}
