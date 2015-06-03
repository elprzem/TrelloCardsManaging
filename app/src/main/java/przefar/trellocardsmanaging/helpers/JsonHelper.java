package przefar.trellocardsmanaging.helpers;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Przemys?aw Farion on 6/2/2015.
 */
public class JsonHelper {
    private static final String LogTag = "JsonHelper";

    public static String getJSON(String url){
        DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
        HttpGet httpGet = new HttpGet(url);

        httpGet.setHeader("Content-type", "application/json");

        InputStream inputStream = null;
        String result = null;
        try {
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            inputStream = entity.getContent();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line).append("\n");
            }
            result = sb.toString();
        } catch (Exception e) {
            Log.e(LogTag, e.toString());
        }
        finally {
            try{
                if(inputStream != null)
                    inputStream.close();
            } catch(Exception e){
                Log.e(LogTag, e.getMessage());
            }
        }
        Log.i(LogTag, "getJSON: result == " + result);
        return result;
    }
}
