package info.smallfield.comic2ical.controller.addcal;

import info.smallfield.comic2ical.model.Cal;
import info.smallfield.comic2ical.util.CommonUtil;

import java.util.Arrays;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        String keywords = asString("keywords");
        if(keywords.length() == 0){
            return null;
        }
        String[] keyword_array = keywords.split("[\\r\\n\\t 　]+");

        List<String> keyword_list = Arrays.asList(keyword_array);

        String url_param = CommonUtil.generateShortenString(keywords);

        Key key = Datastore.createKey(Cal.class, url_param);
        Cal cal = new Cal();
        cal.setKeywords(keyword_list);
        cal.setKey(key);

        Transaction tx = Datastore.beginTransaction();
        Datastore.put(cal);
        tx.commit();
        String url = request.getRequestURL().toString().replaceAll("(^[^/]+//[^/]+/).*", "$1") + "cal/"+url_param;
        requestScope("keywords", keyword_list);
        requestScope("url",url);
        requestScope("webcalurl", url.replaceAll("^[^/]+://(.*)$", "webcal://$1"));
        return forward("index.jsp");
    }
}
