package info.smallfield.comic2ical.controller.cron;

import info.smallfield.comic2ical.model.ReleaseDate;
import info.smallfield.comic2ical.util.AmazonUtil;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

public class GetamazonurlController extends Controller {

    @Override
    public Navigation run() throws Exception {
        Key key = asKey("key");
        if (key == null) {
            return null;
        }
        ReleaseDate calData = Datastore.get(ReleaseDate.class, key);
        if (calData == null || calData.getAmazonUrl() != null) {
            return null;
        }

        AmazonUtil au = new AmazonUtil();
        calData.setAmazonUrl(au.findItem(calData.getTitle()));
        Datastore.put(calData);

        return null;
    }
}
