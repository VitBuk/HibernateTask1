package com.vitbuk.spring;

import com.vitbuk.spring.model.Principal;
import com.vitbuk.spring.model.School;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Principal.class);
        configuration.addAnnotatedClass(School.class);

        SessionFactory sessionFactory =  configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            //task 1
            Principal principal = session.get(Principal.class, 3);
            System.out.println(principal.getSchool().getSchool_number());

            //task2
            School school = session.get(School.class, 4);
            System.out.println("Principal of school " + school.getSchool_number()
                    +" is: " + school.getPrincipal().getName());

            //task3
//            Principal principal1 = new Principal("Rita", 27);
//            School school1 = new School(999, new Principal("Rita", 27));
//            principal1.setSchool(school1);
//            session.save(principal1);

            //task4
            Principal principal2 = new Principal("Olja", 31);
            session.save(principal2);
            School school2 = session.get(School.class, 3);
            school2.setPrincipal(principal2);
            session.update(school2);


            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
