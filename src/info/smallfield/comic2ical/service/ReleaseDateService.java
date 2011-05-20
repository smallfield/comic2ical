package info.smallfield.comic2ical.service;

import info.smallfield.comic2ical.meta.ReleaseDateMeta;
import info.smallfield.comic2ical.model.ReleaseDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slim3.datastore.Datastore;

public class ReleaseDateService {

    ReleaseDateMeta m = ReleaseDateMeta.get();

    public List<ReleaseDate> findByAuthors(List<String> authors) {
        List<ReleaseDate> res = new ArrayList<ReleaseDate>();
        for (String author : authors) {
            res.addAll(Datastore
                .query(m)
                .filter(m.authorFlagment.startsWith(author))
                .asList());
        }
        return res;
    }

    public List<ReleaseDate> findByAuthors(String author) {
        return findByAuthors(Arrays.asList(author));
    }

    public void deleteAll() {
        Datastore.delete(Datastore.query(m).asKeyList());
    }
}
