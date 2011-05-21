package info.smallfield.comic2ical.controller.cal;

import info.smallfield.comic2ical.model.ReleaseDate;
import info.smallfield.comic2ical.service.ReleaseDateService;
import info.smallfield.comic2ical.util.AmazonUtil;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Url;
import net.fortuna.ical4j.model.property.Version;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class IndexController extends Controller {

    ReleaseDateService rds = new ReleaseDateService();

    @Override
    public Navigation run() throws Exception {

        net.fortuna.ical4j.model.Calendar cal =
            new net.fortuna.ical4j.model.Calendar();
        cal.getProperties().add(
            new ProdId("-//Small Field//Comic2iCal 1.0//EN"));
        cal.getProperties().add(Version.VERSION_2_0);
        cal.getProperties().add(CalScale.GREGORIAN);

        List<String> authors = new ArrayList<String>();
        authors.add("吉崎");
        authors.add("荒木");

        List<String> titles = new ArrayList<String>();
        titles.add("ビリー");
        titles.add("コナン");
        titles.add("ボール");

        Set<ReleaseDate> set = new HashSet<ReleaseDate>();
        set.addAll(rds.findByAuthors(authors));
        set.addAll(rds.findByTitles(titles));

        AmazonUtil au = new AmazonUtil();

        for (ReleaseDate rd : set) {
            VEvent date =
                new VEvent(new Date(rd.getDate()), "『" + rd.getTitle() + "』発売日");
            date
                .getProperties()
                .add(
                    new Uid(System.currentTimeMillis()
                        + "@comic2ical.appspot.com"));

            Url url = new Url(new URI(au.findItem(rd.getTitle())));
            date.getProperties().add(url);

            StringBuffer description = new StringBuffer();
            if (rd.getAuthor() != null) {
                description.append("作者: " + rd.getAuthor() + "\n");
            }
            if (rd.getPrice() > 0) {
                description.append("価格: " + rd.getPrice() + "円\n");
            }
            if (url.getValue().length() > 0) {
                description.append("Amazonで購入: " + url.getValue());
            }
            date.getProperties().add(new Description(description.toString()));

            cal.getComponents().add(date);
        }
        response.setContentType("text/calendar");
        final CalendarOutputter output = new CalendarOutputter();
        output.output(cal, response.getOutputStream());
        return null;
    }
}
