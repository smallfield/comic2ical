package info.smallfield.comic2ical.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ConfigureUtilTest {

    ConfigureUtil cu;

    @Before
    public void setUp() throws Exception {
        cu = ConfigureUtil.getInstance();
    }

    @Test
    public void testGet() {
        assertNotNull(cu.get(ConfigureUtil.AMAZON_AWS_ACCESS_KEY_ID));
    }

}
