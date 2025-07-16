package com.roninsoft.hibernate.demo;

import com.roninsoft.hibernate.demo.entity.Address;
import com.roninsoft.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Set;

public class CreateStudentAddressDemo {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Address.class)
                .buildSessionFactory();

        // create session

        try (factory; Session session = factory.getCurrentSession()) {
            // create the object
            Student student = new Student("Maria", "Giovanna", "m.giovanna@gmail.com");
            Address address = new Address("Via Da Qui", "Roma", "0686");
            Address billingAddress = new Address("Via Le mani", "Milano", "0225");

            // embed the address
            student.setHomeAddress(address);
            student.setBillingAddress(billingAddress);
            
            // start a transaction
            session.beginTransaction();

            // save the object
            System.out.println("Saving the student and address...");
            session.persist(student);

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Saving done!");
        }
    }
}
