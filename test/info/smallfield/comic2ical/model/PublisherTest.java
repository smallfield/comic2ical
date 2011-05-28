package info.smallfield.comic2ical.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class PublisherTest extends AppEngineTestCase {

    private Publisher model = new Publisher();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
