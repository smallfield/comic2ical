package info.smallfield.comic2ical.controller.cron;

import info.smallfield.comic2ical.model.ReleaseDate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Transaction;

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

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/m/d");

            while ((line = reader.readLine()) != null
                && (data = line.split("\t", -1)).length == 7) {
                ReleaseDate rd = new ReleaseDate();
                try {
                    rd.setDate(sdf.parse(data[2]));
                } catch (ParseException e) {
                    continue;
                }
                rd.setPublisher(data[0]);
                rd.setSeries(data[1]);
                rd.setTitle(data[3]);
                rd.setAuthor(data[4]);
                rd.setPrice(Integer.valueOf(data[5]));

                Transaction tx = Datastore.beginTransaction();
                Datastore.put(rd);
                tx.commit();
            }
            reader.close();

        } catch (MalformedURLException e) {

        } catch (IOException e) {
            logger.info("");
        }
        return null;

    }
}
