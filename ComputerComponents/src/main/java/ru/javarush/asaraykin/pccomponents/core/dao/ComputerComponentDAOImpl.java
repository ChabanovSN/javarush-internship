package ru.javarush.asaraykin.pccomponents.core.dao;

import org.hibernate.query.NativeQuery;
import org.hibernate.type.IntegerType;
import ru.javarush.asaraykin.pccomponents.core.model.ComputerComponent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Repository
public class ComputerComponentDAOImpl implements ComputerComponentDAO {
    private int numberOfRows;

    public int getNumberOfRows() {
        return numberOfRows;
    }

    @Autowired
    public SessionFactory sessionFactory;

    @Override
    public List<ComputerComponent> findByName(String name) {
        List<ComputerComponent> componentList;
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        NativeQuery query = session.createNativeQuery(String.format("SELECT * FROM components WHERE name = '%s'", name));
        query.addEntity(ComputerComponent.class);
        componentList = query.list();
        numberOfRows = componentList.size();
        tx1.commit();
        session.close();
        return componentList;
    }

    @Override
    public List<ComputerComponent> getAllComponents() {
        List<ComputerComponent> componentList;
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        NativeQuery query = session.createNativeQuery("SELECT * FROM components");
        query.addEntity(ComputerComponent.class);
        componentList = query.list();
        numberOfRows = componentList.size();
        tx1.commit();
        session.close();
        return componentList;
    }

    @Override
    public List<ComputerComponent> filterByIsNecessary(List<ComputerComponent> computerComponents, int isNecessary) {
        List<ComputerComponent> componentList = new ArrayList<>();
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        NativeQuery query = session.createNativeQuery("SELECT * FROM components WHERE isNecessary = " + isNecessary);
        query.addEntity(ComputerComponent.class);
        componentList = query.list();
        Collections.sort(componentList, new Comparator<ComputerComponent>() {
            @Override
            public int compare(ComputerComponent o1, ComputerComponent o2) {
                return o1.getAmount() - o2.getAmount();
            }
        });
        numberOfRows = componentList.size();
        tx1.commit();
        session.close();
        return componentList;
    }

    @Override
    public List<ComputerComponent> getComponentsByPage(int pageNumber, int stringsPerPage, String showFilter) {
        List<ComputerComponent> componentList = new ArrayList<>();
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();

        int offset = (pageNumber - 1) * stringsPerPage;

        if (showFilter.equals("showNecessary")) {
            showFilter = "WHERE isNecessary = 1";
        } else {
            if (showFilter.equals("showUnnecessary")) {
                showFilter = "WHERE isNecessary = 0";
            } else {
                showFilter = "";
            }
        }
        numberOfRows = (Integer) session.createNativeQuery("select count(1) from components " + showFilter)
                .addScalar("count(1)", new IntegerType())
                .uniqueResult();


        NativeQuery query = session.createNativeQuery(String.format("SELECT * FROM components  %s ORDER BY id LIMIT %d , %d ",
                showFilter, offset, stringsPerPage));

        query.addEntity(ComputerComponent.class);
        componentList = query.list();

        tx1.commit();
        session.close();
        return componentList;
    }

    @Override
    public ComputerComponent findById(int id) throws IllegalArgumentException {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        ComputerComponent computerComponent = session.get(ComputerComponent.class, id);
        tx1.commit();
        session.close();
        return computerComponent;
    }


    @Override
    public void deleteComputerComponent(ComputerComponent computerComponent) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(computerComponent);
        tx1.commit();
        session.close();
    }

    @Override
    public void saveOrUpdateComputerComponent(ComputerComponent computerComponent) throws PersistenceException {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(computerComponent);
        tx1.commit();
        session.close();
    }
}
