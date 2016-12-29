package agh.cs.lab9;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by yurii on 12/18/16.
 */
public class UrlReader {

   public String readFromUrl(String urlPath) throws Exception {
        URL website = new URL(urlPath);
        URLConnection connection = website.openConnection();
        InputStream inputStream = connection.getInputStream();
        InputStreamReader inReader = new InputStreamReader(inputStream);
        BufferedReader in = new BufferedReader(inReader);
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);
        in.close();
        return response.toString();
    }

}
