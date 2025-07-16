package com.roninsoft.hibernate.demo;

import com.roninsoft.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetStudentImagesDemo {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        try (factory; Session session = factory.getCurrentSession()) {
            // start a transaction
            session.beginTransaction();

            // get student id
            int id = 1;
            Student student = session.get(Student.class, id);

            // print student details
            System.out.println("Student details: " + student);

            // print the images
            System.out.println("The associated images: " + student.getImages());

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("done!");
        }
    }
}
