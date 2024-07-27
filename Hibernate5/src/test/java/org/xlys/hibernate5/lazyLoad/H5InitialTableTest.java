package org.xlys.hibernate5.lazyLoad;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import org.xlys.hd.pojo.Account;
import org.xlys.hd.pojo.Customer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.xlys.hd.constants.Constants.DEFAULT_RECORD_CREATOR;

/**
 * TODO
 *
 * @author Administrator
 * @date 2024/7/26 10:57 PM
 */
@DisplayName("UT===> Initialize DB tables with Hibernate 5.X")
@Slf4j
public class H5InitialTableTest {

    static SessionFactory sessionFactory;

    @BeforeAll
    public static void initContext() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
                .build();
        try {
//            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
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
    @Disabled
    public void testInsertCustomer() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Account account = session.find(Account.class, 1);
        Customer customer = Customer.builder()
                .firstName("Xia")
                .lastName("Michael")
                .address("Shanghai PuDong district")
                .account(account)
                .isDeleted(0)
                .creator(DEFAULT_RECORD_CREATOR)
                .createTime(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        session.save(customer);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    @Disabled
    @DisplayName("UT--->insert account")
    public void testInsertAccount() throws IOException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Account account = Account.builder()
                .accountName("AoL-X")
                .logo(Files.readAllBytes(new File("/Users/XiaLIjun/Desktop/稳重男士必备小程序开发/avator.jpeg").toPath()))
                .isDeleted(0)
                .createdBy(DEFAULT_RECORD_CREATOR)
                .createdTime(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        session.save(account);
        session.getTransaction().commit();
        session.close();
    }

}
