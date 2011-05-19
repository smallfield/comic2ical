package info.smallfield.comic2ical.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ReleaseDateTest extends AppEngineTestCase {

    private ReleaseDate model = new ReleaseDate();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
