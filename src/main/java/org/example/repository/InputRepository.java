package org.example.repository;

import org.example.dto.Input;
import org.example.dto.Output;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class InputRepository {
    public static void saveInput(List<Output> output, Input input) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();;
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        input.setOutputs(output);
        session.save(input);
        t.commit();

        factory.close();
        session.close();
    }
}
