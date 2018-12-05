import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UserTest {

    @Test
    public void testGetSystemEnvironment() {
        String driverClass = System.getenv("DATABASE_DRIVER_CLASS");
        assertNotNull(driverClass);
    }
}
