package info.smallfield.comic2ical.controller.admin;

import info.smallfield.comic2ical.service.PublisherService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class GetpublisherController extends Controller {

    @Override
    public Navigation run() throws Exception {
        PublisherService ps = new PublisherService();
        requestScope("list", ps.getList());
        return forward("getpublisher.jsp");
    }
}
