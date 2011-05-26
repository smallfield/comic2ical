package info.smallfield.comic2ical.service;

import info.smallfield.comic2ical.meta.PublisherMeta;
import info.smallfield.comic2ical.model.Publisher;

import org.slim3.datastore.Datastore;

public class PublisherService {
    PublisherMeta m = PublisherMeta.get();

    public Publisher findByName(String name) {
        return Datastore.query(m).filter(m.name.equal(name)).asSingle();
    }

    public Publisher addPublisher(String publisher_name) {
        Publisher res;

        if ((res = this.findByName(publisher_name)) == null) {
            res = new Publisher();
            res.setName(publisher_name);
            Datastore.put(res);
        }
        return res;
    }

}
