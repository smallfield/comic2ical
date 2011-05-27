package info.smallfield.comic2ical.controller.admin;

import info.smallfield.comic2ical.service.SeriesService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class GetseriesController extends Controller {

    @Override
    public Navigation run() throws Exception {
        SeriesService ss = new SeriesService();

        requestScope("list", ss.getList());
        return forward("getseries.jsp");
    }
}
