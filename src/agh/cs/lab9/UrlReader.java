package agh.cs.lab9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

/**
 * Created by yurii on 12/18/16.
 */
public class UrlReader {

    public String readFromUrl(String urlPath) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlPath);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    public Reader readFromUrl2(String urlPath) throws Exception {
        URL url = new URL(urlPath);
        Reader reader = new InputStreamReader(url.openStream());
        return reader;
    }
}
