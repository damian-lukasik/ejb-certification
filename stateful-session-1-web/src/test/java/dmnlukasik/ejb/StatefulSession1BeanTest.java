package dmnlukasik.ejb;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import static org.junit.Assert.assertTrue;

public class StatefulSession1BeanTest {

    private static EJBContainer ejbContainer;
    private static Context context;

    @BeforeClass
    public static void beforeClass() {
        ejbContainer = EJBContainer.createEJBContainer();
        context = ejbContainer.getContext();
    }

    @AfterClass
    public static void afterClass() {
        ejbContainer.close();
    }

    @Test
    public void shouldGreet() throws NamingException {
        StatefulSession1Bean bean = (StatefulSession1Bean) context.lookup("java:global/classes/StatefulSession1Bean");

        assertTrue(bean.greeting("Damian").contains("Damian"));
    }
}
