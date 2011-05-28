package info.smallfield.comic2ical.service;

import info.smallfield.comic2ical.meta.PublisherMeta;
import info.smallfield.comic2ical.model.Publisher;

import java.util.List;
import java.util.logging.Logger;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Transaction;

public class PublisherService {
    PublisherMeta m = PublisherMeta.get();
    Logger logger = Logger.getLogger(this.getClass().getName());

    public Publisher findByName(String name) {
        return Datastore.query(m).filter(m.name.equal(name)).asSingle();
    }

    public Publisher addPublisher(String publisher_name) {
        Publisher res;

        if ((res = this.findByName(publisher_name)) == null) {
            res = new Publisher();
            res.setName(publisher_name);
            logger.info("Add publisher: " + publisher_name);
            Transaction tx = Datastore.beginTransaction();
            Datastore.put(res);
            tx.commit();
        }
        return res;
    }

    public List<Publisher> getList() {
        return Datastore.query(m).asList();
    }

    public void deleteAll() {
        Datastore.delete(Datastore.query(m).asKeyList());
    }

}
