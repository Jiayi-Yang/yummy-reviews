package org.example.repository;

import org.example.model.Item;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemDaoImpl implements ItemDao{
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public Item save(Item item) {
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        try {
            transaction = s.beginTransaction();
            s.saveOrUpdate(item);
            transaction.commit();
            s.close();
            return item;
        } catch (Exception e){
            if (transaction != null) transaction.rollback();
            logger.error("fail to insert record");
            s.close();
            return null;
        }
    }

    @Override
    public Item update(Item item) {
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        try {
            transaction = s.beginTransaction();
            s.saveOrUpdate(item);
            transaction.commit();
            return item;
        } catch (HibernateException e){
            if (transaction != null) transaction.rollback();
            s.close();
            logger.error("unable to update record", e);
            return null;
        }
    }

    @Override
    public boolean delete(Item item) {
        String hql = "DELETE Item as i WHERE i.itemId = :Id";
        int deletedCount = 0;
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        try {
            transaction = s.beginTransaction();
            Query<Item> query = s.createQuery(hql);
            query.setParameter("Id", item.getItemId());
            deletedCount = query.executeUpdate();
            transaction.commit();
            s.close();
            return deletedCount >= 1 ? true : false;
        } catch (HibernateException e){
            if (transaction != null) transaction.rollback();
            s.close();
            logger.error("unable to delete record", e);
        }
        return false;
    }

    @Override
    public List<Item> getItems() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        String hql = "FROM Item";
        s.createQuery(hql);
        List<Item> result = new ArrayList<>();
        try {
            Query query = s.createQuery(hql);
            result = query.list();
            s.close();
        } catch (HibernateException e){
            logger.error("session close exception try again", e);
            s.close();
        }
        return result;
    }

    @Override
    public Item getBy(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        String hql = "FROM Item i WHERE i.itemId=:Id";
        s.createQuery(hql);
        try {
            Query<Item> query = s.createQuery(hql);
            query.setParameter("Id", id);
            Item result = query.uniqueResult();
            s.close();
            return result;
        } catch (HibernateException e) {
            logger.error("session close exception try again", e);
            s.close();
            return null;
        }
    }
}
