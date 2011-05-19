package info.smallfield.comic2ical.controller.cron;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class FetchdataController extends Controller {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public Navigation run() throws Exception {

        try {
            URL url =
                new URL("http://www.mangaoh.co.jp/download/comic/2011/01/");
            HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            BufferedReader reader =
                new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),
                    Charset.forName("sjis")));
            String line;
            String[] data;
            while ((line = reader.readLine()) != null
                && (data = line.split("\t{1}", -1)).length == 7) {
                
            }
            reader.close();

        } catch (MalformedURLException e) {

        } catch (IOException e) {
            logger.info("");
        }
        return null;

    }
}
