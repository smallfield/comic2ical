package info.smallfield.comic2ical.controller;

import org.slim3.controller.router.RouterImpl;

public class AppRouter extends RouterImpl {

    public AppRouter() {
        addRouting("/cal/{key}", "/cal/?key={key}");
    }
}