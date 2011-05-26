package info.smallfield.comic2ical.service;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class PublisherServiceTest extends AppEngineTestCase {

    private PublisherService service = new PublisherService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
