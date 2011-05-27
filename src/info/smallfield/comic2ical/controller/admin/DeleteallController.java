package info.smallfield.comic2ical.controller.admin;

import info.smallfield.comic2ical.service.PublisherService;
import info.smallfield.comic2ical.service.ReleaseDateService;
import info.smallfield.comic2ical.service.SeriesService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class DeleteallController extends Controller {

    @Override
    public Navigation run() throws Exception {

        ReleaseDateService rds = new ReleaseDateService();
        rds.deleteAll();

        SeriesService ss = new SeriesService();
        ss.deleteAll();

        PublisherService ps = new PublisherService();
        ps.deleteAll();

        return null;
    }
}
