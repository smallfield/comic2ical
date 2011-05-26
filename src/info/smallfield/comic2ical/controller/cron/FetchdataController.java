package info.smallfield.comic2ical.controller.cron;

import info.smallfield.comic2ical.model.ReleaseDate;
import info.smallfield.comic2ical.service.ReleaseDateService;
import info.smallfield.comic2ical.service.SeriesService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions.Builder;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

public class FetchdataController extends Controller {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public Navigation run() throws Exception {
        Calendar fetchDate = Calendar.getInstance();
        fetchDate.add(Calendar.MONTH, -1);

        SimpleDateFormat fmtForUrl = new SimpleDateFormat("yyyy/MM/");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d");
        String line;
        String[] data;

        ReleaseDateService rds = new ReleaseDateService();
        SeriesService ss = new SeriesService();

        while (true) {
            try {
                URL url =
                    new URL("http://www.mangaoh.co.jp/download/comic/"
                        + fmtForUrl.format(fetchDate.getTime()));

                logger.info("Start fetching : " + url.toString());

                HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return null;
                }

                BufferedReader reader =
                    new BufferedReader(new InputStreamReader(
                        connection.getInputStream(),
                        Charset.forName("sjis")));

                int count = 0;
                ReleaseDate rd;
                while ((line = reader.readLine()) != null
                    && (data = line.split("\t", -1)).length == 7) {
                    if ((rd = rds.fetchOneByTitle(data[3])) == null) {
                        rd = new ReleaseDate();
                    }
                    try {
                        rd.setDate(sdf.parse(data[2]));
                    } catch (ParseException e) {
                        continue;
                    }

                    rd.getSeriesRef().setModel(ss.addSeries(data[1], data[0]));

                    rd.setTitle(data[3]);
                    rd.setAuthor(data[4]);
                    rd.setPrice(Integer.valueOf(data[5]));

                    Transaction tx = Datastore.beginTransaction();
                    Datastore.put(rd);
                    tx.commit();

                    QueueFactory.getQueue("amazon").add(
                        Builder
                            .withUrl("/cron/getamazonurl")
                            .param("key", KeyFactory.keyToString(rd.getKey()))
                            .method(Method.GET));

                    count++;
                }
                reader.close();
                logger.info("End fetching : " + url.toString());
                logger.info(count + " data was added.");
                if (count == 0) {
                    // none data added
                    return null;
                }
                fetchDate.add(Calendar.MONTH, 1);
            } catch (MalformedURLException e) {

            } catch (IOException e) {
                logger.info("");
            }
        }
    }
}
