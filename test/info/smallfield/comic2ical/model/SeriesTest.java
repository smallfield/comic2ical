package info.smallfield.comic2ical.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class SeriesTest extends AppEngineTestCase {

    private Series model = new Series();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
