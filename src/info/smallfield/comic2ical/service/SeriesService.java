package info.smallfield.comic2ical.service;

import java.util.List;
import java.util.logging.Logger;

import info.smallfield.comic2ical.meta.SeriesMeta;
import info.smallfield.comic2ical.model.Publisher;
import info.smallfield.comic2ical.model.Series;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Transaction;

public class SeriesService {
    SeriesMeta m = SeriesMeta.get();
    Logger logger = Logger.getLogger(this.getClass().getName());

    public static final String OTHER = "その他";

    public Series findByName(String name) {
        return Datastore.query(m).filter(m.name.equal(name)).asSingle();
    }

    public Series addSeries(String series_name, String publisher_name) {
        Series res;
        PublisherService ps = new PublisherService();

        if (series_name.equals(SeriesService.OTHER)) {
            List<Series> list =
                ps
                    .addPublisher(publisher_name)
                    .getSeriesListRef()
                    .getModelList();

            for (Series series : list) {
                if (series.getName().equals(OTHER)) {
                    return series;
                }
            }
        }

        if ((res = this.findByName(series_name)) == null) {
            res = new Series();
            res.setName(series_name);

            Publisher pub = ps.addPublisher(publisher_name);

            res.getPublisherRef().setModel(pub);
            logger.info("Add series: " + res.getName());
            Transaction tx = Datastore.beginTransaction();
            Datastore.put(res);
            tx.commit();
        }

        return res;
    }

    public List<Series> getList() {
        return Datastore.query(m).asList();
    }

    public void deleteAll() {
        Datastore.delete(Datastore.query(m).asKeyList());
    }
}
