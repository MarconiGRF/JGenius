package JavaGenius;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static com.sun.xml.internal.ws.policy.subject.WsdlBindingSubject.WsdlMessageType.OUTPUT;

public class RequestContent {

    RequestContent(){
        String finalAuthorizationCode = "";
    }

    public String doRequestContent(String searchContent, MainController clientDetails) throws IOException{


        ServerSocket scklsn = new ServerSocket(57454); //Open Socket
        Socket ServSck = scklsn.accept(); //Listen to it
        InputStream inSrvSck = ServSck.getInputStream(); //Here you can receive data from it.
        OutputStream outSrvSck = ServSck.getOutputStream(); //Here you can send data from it.

        //POST Requesto to GeniusAPI
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://api.genius.com/search");
        // Requested parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("User-Agent", "JavaGenius/0.1-alpha"));
        params.add(new BasicNameValuePair("Accept", "application/json"));
        params.add(new BasicNameValuePair("Host", "api.genius.com"));
        params.add(new BasicNameValuePair("Authorization","Bearer " + clientDetails.authcode));
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
