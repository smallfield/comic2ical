package info.smallfield.comic2ical.service;

import info.smallfield.comic2ical.meta.SeriesMeta;
import info.smallfield.comic2ical.model.Publisher;
import info.smallfield.comic2ical.model.Series;

import org.slim3.datastore.Datastore;

public class SeriesService {
    SeriesMeta m = SeriesMeta.get();

    public Series findByName(String name) {
        return Datastore.query(m).filter(m.name.equal(name)).asSingle();
    }

    public Series addSeries(String series_name, String publisher_name) {
        Series res;

        if ((res = this.findByName(series_name)) == null) {
            res = new Series();
            res.setName(series_name);

            PublisherService ps = new PublisherService();
            Publisher pub = ps.addPublisher(publisher_name);

            res.getPublisherRef().setModel(pub);
            Datastore.put(res);
        }

        return res;
    }
}
