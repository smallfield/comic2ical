package info.smallfield.comic2ical.controller;

import info.smallfield.comic2ical.service.ReleaseDateService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        ReleaseDateService rds = new ReleaseDateService();
        this.requestScope("count", rds.count());
        return forward("index.jsp");
    }
}
