package prag.jacksonXml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import prag.jacksonXml.IChangeNotifyHandler;

public class NetAppCmodeChangeNotifyHandler implements IChangeNotifyHandler {

    // Constants
    private static final int KDFPOLICY_CHANGE_NOTIFY_PORT = 54222;
    private static final int PREFIX_LEN = 6;
    private static final int PAYLOAD_LENGTH = 4;
    private static final int PAYLOAD_LENGTH_START_INDEX = 1;
    private static final int PAYLOAD_LENGTH_END_INDEX = 4;
    private static final int ASCII_DOUBLE_QUOTE = 34;

    // Class Variables
    private String response;
    private String requestType;
    private String requestHeader;
    private String actualRequest;
    private ServerSocket serverSocket;

    @Override
    public void setupChangeNotifyHandler(boolean running) {
        try {
            startFPolicyListener(running);
        } catch (Exception e) {
            System.out.println("Failed to setup NetApp Cmode change notify handler"+ e);
        }
    }

    // TODO (Pragad): Add shutdown logic for change notify
    public void shutdownChangeNotifyHandler() {
    }

    @SuppressWarnings("resource")
    public void startFPolicyListener(boolean running) throws Exception {
        Socket connectionSocket = null;
        try {
            // Open up the socket and start listening on it for requests from NetApp's FPolicy engine
            serverSocket = new ServerSocket(KDFPOLICY_CHANGE_NOTIFY_PORT);
            while (running) {
                connectionSocket = serverSocket.accept();
                // TODO (Pragad): Make this multi-threaded so that each request can be processed individually
                handleRequest(connectionSocket);
            }
        } catch (IOException e) {
            System.out.println("Failed to create socket for FPolicy communication"+ e);
            throw new Exception("Failed to create socket for FPolicy communication", e);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (connectionSocket != null) {
                    connectionSocket.close();
                }
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (Exception e) {
                System.out.println("Failed to close the sockets"+ e);
            }
        }
    }

    // This is the main function that takes care of the following
    // 1. Read request from the socket
    // 2. Get the request type and process the request
    // 3. TODO (Pragad): Pass the request got from NetApp to backend so that changes can be reflected on the target
    // 4. Send necessary response to NetApp
    public void handleRequest(Socket connectionSocket) throws Exception {
        String multiLineRequest = readFromSocket(connectionSocket);
        getRequestType(multiLineRequest);
        AbstractNetAppFPolicyRequest reqHandler = getRequestHandler();
        if (reqHandler == null) {
            System.out.println("Invalid request: {}"+ multiLineRequest);
            return;
        }
        response = reqHandler.handleRequest();
        if (response == null || response.isEmpty()) {
            System.out.println("Failed to generated response for request: {}"+ multiLineRequest);
        }
        writeToSocket(connectionSocket);
    }

    public String readFromSocket(Socket connectionSocket) throws Exception {
        try {
            // From the first six bytes read, get the length of the request and read the request from the socket
            int reqLength = getRequestLengthFromSocket(connectionSocket);
            int temp = reqLength;
            int numBytesReceived;
            StringBuilder requestStr = new StringBuilder();
            // Read from socket till the request length is reached
            byte[] buffer = new byte[reqLength];
            while (temp > 0) {
                numBytesReceived = connectionSocket.getInputStream().read(buffer);
                requestStr.append(new String(buffer, reqLength - temp, numBytesReceived, StandardCharsets.UTF_8));
                temp -= numBytesReceived;
            }
            System.out.println("Request Received: {}"+ requestStr);
            return requestStr.toString();
        } catch (Exception e) {
            System.out.println("Failed to read requeset from socket: {}"+ connectionSocket);
            throw new Exception("Failed to read requeset from socket " + connectionSocket, e);
        }
    }

    public int getRequestLengthFromSocket(Socket connectionSocket) throws Exception {
        try {
            // Read the first six bytes to get the request length
            byte[] reqPrefix = new byte[PREFIX_LEN];
            int numBytesReceived = 0;
            int counter = 0;
            // Give up after 5 seconds
            while (numBytesReceived == 0 && counter < 5) {
                numBytesReceived = connectionSocket.getInputStream().read(reqPrefix);
                if (numBytesReceived == 0) {
                    try {
                        Thread.sleep( 1000L );
                        counter++;
                        System.out.println("Received zero bytes from socket. Attempting a retry after one second");
                    } catch (InterruptedException e) {
                        System.out.println("Exception on sleep while trying to read from the socket");
                        break;
                    }
                }
            }
            if (numBytesReceived != PREFIX_LEN) {
                String rp = new String(reqPrefix, StandardCharsets.UTF_8);
                System.out.println("Failed to read request prefix. Bytes received: {}, buffer received: {}"+ rp);
                throw new Exception("Failed to read request prefix. Bytes received: " + numBytesReceived + ", buffer received: " + rp);
            }
            // The first character or the second character must be a "double quote"
            int reqLenOffset = PAYLOAD_LENGTH_START_INDEX;
            if (reqPrefix[0] != ASCII_DOUBLE_QUOTE) {
                if (reqPrefix[1] != ASCII_DOUBLE_QUOTE) {
                    String rp = new String(reqPrefix, StandardCharsets.UTF_8);
                    System.out.println("Invalid request prefix: {}"+ rp);
                    throw new Exception("Invalid request prefix " + rp);
                }
                // As the first character is not a double quote read one more byte from the socket and check if it is a quote
                reqLenOffset = PAYLOAD_LENGTH_START_INDEX + 1;
                byte[] reqPrefixLastByte = new byte[1];
                numBytesReceived = connectionSocket.getInputStream().read(reqPrefixLastByte);
                if ((numBytesReceived != 1) || (reqPrefixLastByte[0] != ASCII_DOUBLE_QUOTE)) {
                    String rp = new String(reqPrefix, StandardCharsets.UTF_8);
                    System.out.println("Failed to read trailing quote of request prefix. Bytes received: {}, buffer received: {}"+ rp);
                    throw new Exception("Failed to read trailing quote of request prefix. Bytes received: " + numBytesReceived + ", buffer received: " + rp);
                }
            }
            // Get the request length by converting the buffer from big-endian to little-endian
            int reqLength = ByteBuffer.wrap(reqPrefix, reqLenOffset, reqLenOffset + PAYLOAD_LENGTH - 1).order(ByteOrder.BIG_ENDIAN).getInt();
            System.out.println("Length of request: {}"+ reqLength);
            return reqLength;
        } catch (Exception e) {
            System.out.println("Failed to read prefix from socket"+ e);
            throw new Exception("Failed to read prefix from socket", e);
        }
    }

    public void getRequestType(String request) throws Exception {
        try {
            List<String> requestLines = Arrays.asList(request.split("[\\r\\n]+"));
            requestHeader = requestLines.get(0);
            actualRequest = requestLines.get(1);
            XmlMapper xmlMapper = new XmlMapper();
            NetAppFPolicyHeaderSchema header = xmlMapper.readValue(requestHeader, NetAppFPolicyHeaderSchema.class);
            requestType = header.notfType;
            System.out.println("Got '{}' request"+ requestType);
        } catch (Exception e) {
            System.out.println("Failed to read request header: {}"+ requestHeader+ e);
            throw new Exception("Failed to read request header: " + requestHeader, e);
        }
    }

    public AbstractNetAppFPolicyRequest getRequestHandler() {
        switch (requestType) {
            case "NEGO_REQ":
                return new NetAppFPolicyHandshakeRequestHandler(actualRequest);
            default:
                break;
        }
        System.out.println("Invalid request type for request: {}"+ actualRequest);
        return null;
    }

    public byte[] constructFinalResponse() {
        // First 6 bytes of the final response will include the Length of the data payload in big-endian format
        // Double quotes are required to enclose the length of the data payload
        System.out.println("Construct final response for '{}'"+ requestType);
        byte[] initialResp = response.getBytes(StandardCharsets.UTF_8);
        byte[] finalResp = new byte[PREFIX_LEN + initialResp.length];
        finalResp[0] = finalResp[PREFIX_LEN - 1] = ASCII_DOUBLE_QUOTE;
        System.arraycopy(initialResp, 0, finalResp, PREFIX_LEN, initialResp.length);
        // Copy the response length between bytes 1 and 4 in the final response by converting it to big-endian
        ByteBuffer bb = ByteBuffer.allocate(PAYLOAD_LENGTH);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.putInt(response.length());
        System.arraycopy(bb.array(), 0, finalResp, PAYLOAD_LENGTH_START_INDEX, PAYLOAD_LENGTH_END_INDEX);
        System.out.println("Constructed final response '{}'"+ new String(finalResp, StandardCharsets.UTF_8));
        return finalResp;
    }

    public void writeToSocket(Socket connectionSocket) throws Exception {
        try {
            byte[] finalResponse = constructFinalResponse();
            @SuppressWarnings("resource")
            DataOutputStream outStream = new DataOutputStream(connectionSocket.getOutputStream());
            outStream.write(finalResponse, 0, finalResponse.length);
            outStream.close();
            System.out.println("Response sent: {}"+ new String(finalResponse, StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.out.println("Failed to write response to socket"+ e);
            throw new Exception("Failed to write response to socket", e);
        }
    }

    public static void main(String[] args) {
        NetAppCmodeChangeNotifyHandler changeNotifyHandler = new NetAppCmodeChangeNotifyHandler();
        changeNotifyHandler.setupChangeNotifyHandler(true);
    }
}
