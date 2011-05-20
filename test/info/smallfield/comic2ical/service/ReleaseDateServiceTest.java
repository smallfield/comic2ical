package info.smallfield.comic2ical.service;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ReleaseDateServiceTest extends AppEngineTestCase {

    private ReleaseDateService service = new ReleaseDateService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));

        service.findByAuthors("小");
    }

}
