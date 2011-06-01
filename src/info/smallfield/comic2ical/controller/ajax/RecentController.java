package info.smallfield.comic2ical.controller.ajax;

import info.smallfield.comic2ical.service.ReleaseDateService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class RecentController extends Controller {
    ReleaseDateService rds = new ReleaseDateService();

    @Override
    public Navigation run() throws Exception {
        this.requestScope("list", rds.getRecentRelease());
        return forward("recent.jsp");
    }
}
