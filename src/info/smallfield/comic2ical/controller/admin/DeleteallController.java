package info.smallfield.comic2ical.controller.admin;

import info.smallfield.comic2ical.service.ReleaseDateService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class DeleteallController extends Controller {

    @Override
    public Navigation run() throws Exception {

        ReleaseDateService rds = new ReleaseDateService();
        rds.deleteAll();
        return null;
    }
}
