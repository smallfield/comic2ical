package info.smallfield.comic2ical.controller.cal;

import java.util.Calendar;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Version;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {

        net.fortuna.ical4j.model.Calendar cal =
            new net.fortuna.ical4j.model.Calendar();
        cal.getProperties().add(new ProdId("-//Small Field//Comic2iCal 1.0//EN"));
        cal.getProperties().add(Version.VERSION_2_0);
        cal.getProperties().add(CalScale.GREGORIAN);

        Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(java.util.Calendar.MONTH, java.util.Calendar.DECEMBER);
        calendar.set(java.util.Calendar.DAY_OF_MONTH, 25);

        VEvent christmas =
            new VEvent(new Date(calendar.getTime()), "クリスマスの日だよ");
        christmas.getProperties().add(
            new Uid(System.currentTimeMillis() + "@comic2ical.appspot.com"));
        christmas.getProperties().add(new Description("日本語でOK？"));

        cal.getComponents().add(christmas);

        response.setContentType("text/calendar");
        final CalendarOutputter output = new CalendarOutputter();
        output.output(cal, response.getOutputStream());
        return null;
    }
}
