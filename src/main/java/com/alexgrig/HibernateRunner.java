package com.alexgrig;


import com.alexgrig.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunner {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        //configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        //configuration.addAnnotatedClass(User.class);  // либо маппинг в конфигурационном xml
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            User user = User.builder()
                    .username("alex@mail.ru")
                    .firstname("Alex")
                    .lastname("Alexovok")
                    .birthDate(LocalDate.of(1999, 6, 25))
                    .age(23)
                    .build();

            session.save(user);

            session.getTransaction().commit();

        }

    }
}
