package org.example.repository;

import org.example.dto.Search;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class SearchRepository {
    public static Search getSearchById(int id) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();;
        Session session = factory.openSession();

        Search search =  session.get(Search.class, id);

        factory.close();
        session.close();

        return search;
    }

    public static void createSearch(String lotName, String lotPlan) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();;
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Search search = new Search();
        search.setLotName(lotName);
        search.setLotPlan(lotPlan);
        session.save(search);
        t.commit();

        factory.close();
        session.close();
    }
}
