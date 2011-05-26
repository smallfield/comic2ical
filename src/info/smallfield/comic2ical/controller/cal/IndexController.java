package info.smallfield.comic2ical.controller.cal;

import info.smallfield.comic2ical.model.Cal;
import info.smallfield.comic2ical.model.ReleaseDate;
import info.smallfield.comic2ical.service.ReleaseDateService;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletOutputStream;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Url;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.model.property.XProperty;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

public class IndexController extends Controller {

    ReleaseDateService rds = new ReleaseDateService();

    @Override
    public Navigation run() throws Exception {
        Key key = Datastore.createKey(Cal.class, asString("key"));
        if (key == null) {
            return null;
        }
        Cal calData = Datastore.get(Cal.class, key);
        if (calData == null) {
            return null;
        }

        response.setContentType("text/calendar; charset=utf-8;");

        final CalendarOutputter output = new CalendarOutputter();

        if (calData.getCalendarOutput() != null
            && request.getParameter("get") == null) {
            output.output(
                calData.getCalendarOutput(),
                response.getOutputStream());
        }

        net.fortuna.ical4j.model.Calendar cal =
            new net.fortuna.ical4j.model.Calendar();
        cal.getProperties().add(
            new ProdId("-//Small Field//Comic2iCal 1.0//EN"));
        cal.getProperties().add(Version.VERSION_2_0);
        cal.getProperties().add(CalScale.GREGORIAN);
        cal.getProperties().add(new XProperty("X-WR-CALNAME", "こみかるカレンダー"));

        Set<ReleaseDate> set = new HashSet<ReleaseDate>();
        set.addAll(rds.findByAuthors(calData.getKeywords()));
        set.addAll(rds.findByTitles(calData.getKeywords()));

        for (ReleaseDate rd : set) {
            VEvent date =
                new VEvent(new Date(rd.getDate()), "『" + rd.getTitle() + "』発売日");
            date.getProperties().add(
                new Uid(rd.getKey().hashCode() + "@comic2ical.appspot.com"));

            Url url = null;
            try {
                url = new Url(new URI(rd.getAmazonUrl()));
                date.getProperties().add(url);
            } catch (Exception e) {
            }

            StringBuffer description = new StringBuffer();
            if (rd.getAuthor() != null) {
                description.append("作者: " + rd.getAuthor() + "\n");
            }
            if (rd.getSeriesRef().getModel() != null
                && rd.getSeriesRef().getModel().getPublisherRef().getModel() != null) {
                description.append("出版社: "
                    + rd
                        .getSeriesRef()
                        .getModel()
                        .getPublisherRef()
                        .getModel()
                        .getName()
                    + "\n");
            }
            if (url != null && url.getValue().length() > 0) {
                description.append("Amazonで購入: " + url.getValue());
            }
            date.getProperties().add(new Description(description.toString()));

            cal.getComponents().add(date);
        }

        if (set.size() > 0) {
            output.output(cal, response.getOutputStream());
            calData.setCalendarOutput(cal);
            Datastore.putAsync(calData);
        } else {
            ServletOutputStream os = response.getOutputStream();
            os.println("BEGIN:VCALENDAR");
            os.println("PRODID:-//Small Field//Comic2iCal 1.0//EN");
            os.println("VERSION:2.0");
            os.println("CALSCALE:GREGORIAN");
            os.println("END:VCALENDAR");
            os.flush();
            os.close();
        }

        return null;
    }
}
