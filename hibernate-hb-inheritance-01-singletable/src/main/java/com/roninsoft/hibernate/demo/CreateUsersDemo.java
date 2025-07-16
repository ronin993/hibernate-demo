package com.roninsoft.hibernate.demo;

import com.roninsoft.hibernate.demo.entity.Instructor;
import com.roninsoft.hibernate.demo.entity.Student;
import com.roninsoft.hibernate.demo.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateUsersDemo {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Instructor.class)
                .buildSessionFactory();

        // create session

        try (factory; Session session = factory.getCurrentSession()) {
            // create the object
            Student student = new Student("Maria", "Giovanna", "m.giovanna@gmail.com", "hibernate-scam");

            Instructor instructor = new Instructor("Maria", "Giovanna", "m.giovanna@gmail.com", 6759.00);

            // start a transaction
            session.beginTransaction();

            // save the object
            System.out.println("Saving the student and address...");
            session.persist(student);
            session.persist(instructor);

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Saving done!");
        }
    }
}
