package info.smallfield.comic2ical.controller.cal;

import info.smallfield.comic2ical.model.ReleaseDate;
import info.smallfield.comic2ical.service.ReleaseDateService;
import info.smallfield.comic2ical.util.AmazonUtil;

import java.net.URI;
import java.util.Calendar;
import java.util.List;

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

        List<ReleaseDate> list = rds.findByAuthors("飛呂彦");

        for (ReleaseDate rd : list) {

            Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(rd.getDate());

            VEvent date =
                new VEvent(new Date(rd.getDate()), "『" + rd.getTitle() + "』発売日");
            date
                .getProperties()
                .add(
                    new Uid(System.currentTimeMillis()
                        + "@comic2ical.appspot.com"));
            date.getProperties().add(
                new Description(String.format(
                    "作者: %s\n価格: %d円",
                    rd.getAuthor(),
                    rd.getPrice())));

            AmazonUtil au = new AmazonUtil();
            date.getProperties().add(
                new Url(new URI(au.findItem(rd.getTitle()))));

            cal.getComponents().add(date);
        }
        response.setContentType("text/calendar");
        final CalendarOutputter output = new CalendarOutputter();
        output.output(cal, response.getOutputStream());
        return null;
    }
}
