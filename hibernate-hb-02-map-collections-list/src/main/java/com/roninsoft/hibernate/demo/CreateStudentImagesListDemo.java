package com.roninsoft.hibernate.demo;

import com.roninsoft.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Set;

public class CreateStudentImagesListDemo {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session

        try (factory; Session session = factory.getCurrentSession()) {
            // create the object
            Student student = new Student("Maria", "Giovanna", "m.giovanna@gmail.com");
            List<String> images = student.getImages();
            images.addAll(Set.of("photo.jpg", "photo1.jpg", "photo2.jpg", "photo3"));

            // start a transaction
            session.beginTransaction();

            // save the object
            System.out.println("Saving the student and images...");
            session.persist(student);

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Saving done!");
        }
    }
}
