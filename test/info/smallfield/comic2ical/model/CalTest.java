package info.smallfield.comic2ical.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CalTest extends AppEngineTestCase {

    private Cal model = new Cal();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
