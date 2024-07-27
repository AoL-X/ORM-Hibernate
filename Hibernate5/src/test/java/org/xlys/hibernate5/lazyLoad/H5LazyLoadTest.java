package org.xlys.hibernate5.lazyLoad;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.*;
import org.xlys.hd.pojo.Customer;

/**
 * TODO
 *
 * @author Administrator
 * @date 2024/7/26 10:57 PM
 */
@DisplayName("UT===> Lazy Load feature test with Hibernate 5.X")
@Slf4j
public class H5LazyLoadTest {

    static SessionFactory sessionFactory;

    @BeforeAll
    public static void initContext() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    @BeforeEach
    public void logStart() {
        log.info("Test start ...");
    }

    @AfterEach
    public void logEnd() {
        log.info("Test finished ...");
    }

    @Test
//    @Disabled
    public void testLazyLoad() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Customer customer = session.load(Customer.class, 1);
        log.info("Found customer:{}",customer);
        String logoStr = customer.getAccount().getLogo().toString();
        log.info("size of logo:{}",logoStr.length());
        session.getTransaction().commit();
        session.close();
    }

}
