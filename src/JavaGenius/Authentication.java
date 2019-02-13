package JavaGenius;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Authentication {

    Authentication(){
        String finalAuthorizationCode = "";
    }

    public String doAuthentication() throws IOException{
        final String OUTPUT = "<html><head><title>JavaGenius Lyrics</title></head><body>" +
                "<p>Authentication successful, you can close this tab now.</p>" +
                "</body></html>";
        final String OUTPUT_HEADERS = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" +
                "Content-Length: ";
        final String OUTPUT_END_OF_HEADERS = "\r\n\r\n";
        String finalAuthorizationCode = "";

        ServerSocket scklsn = new ServerSocket(57454); //Open Socket
        Socket ServSck = scklsn.accept(); //Listen to it
        InputStream inSrvSck = ServSck.getInputStream(); //Here you can receive data from it.
        OutputStream outSrvSck = ServSck.getOutputStream(); //Here you can send data from it.

        //Save browser's request
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inSrvSck, "UTF-8"));
        String line = null;
        line = bufferedReader.readLine(); //Read data from InputStream, write it into line. (only first necessary).

        //Answer to browser
        BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(
                        new BufferedOutputStream(ServSck.getOutputStream()), "UTF-8"));
        bufferedWriter.write(OUTPUT_HEADERS + OUTPUT.length() + OUTPUT_END_OF_HEADERS + OUTPUT); //Send Answer
        bufferedWriter.flush(); //Clean bufferedWriter's answer.
        bufferedWriter.close();
        ServSck.close(); //Close socket

        String[] auxiliarSplitArray;
        auxiliarSplitArray = line.split("=");
        String geniusCODEParam = auxiliarSplitArray[1].substring(0,auxiliarSplitArray[1].indexOf("&"));
        String geniusState = auxiliarSplitArray[2].substring(0,auxiliarSplitArray[2].indexOf(" "));

        //POST Requesto to GeniusAPI
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://api.genius.com/oauth/token");
        // Requested parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("code", geniusCODEParam));
        params.add(new BasicNameValuePair("client_id", "JAVAGENIUS_CLIENT_ID"));
        params.add(new BasicNameValuePair("client_secret", "JAVAGENIUS_API_SECRET"));
        params.add(new BasicNameValuePair("redirect_uri", "http://localhost:57454/"));
        params.add(new BasicNameValuePair("response_type", "code"));
        params.add(new BasicNameValuePair("grant_type","authorization_code"));
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        //Do POST and get answer
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();

        InputStream inStream = entity.getContent();

        BufferedReader buffReaderGenius = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
        String bodyResponse = null;
        bodyResponse = buffReaderGenius.readLine();
        String[] auxiliarSplitArray2 = bodyResponse.split("\"");
        finalAuthorizationCode = auxiliarSplitArray2[3];

        return finalAuthorizationCode;

    }
}
