package org.example.repository;

import org.example.model.Rating;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RatingDaoImpl implements RatingDao{
    @Autowired
    private SessionFactory sessionFactory;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public Rating save(Rating rating) {
        Transaction transaction = null;
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        try {
            transaction = s.beginTransaction();
            s.saveOrUpdate(rating);
            transaction.commit();
            s.close();
            return rating;
        } catch (Exception e){
            if (transaction != null) transaction.rollback();
            logger.error("fail to insert record");
            s.close();
            return null;
        }
    }

    @Override
    public Rating update(Rating rating) {
        return null;
    }

    @Override
    public boolean delete(Rating rating) {
        String hql = "DELETE Rating as r WHERE r.ratingId = :Id";
        int deletedCount = 0;
        Transaction transaction = null;
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        try {
            transaction = s.beginTransaction();
            Query<Rating> query = s.createQuery(hql);
            query.setParameter("Id", rating.getRatingId());
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
    public List<Rating> getRatings() {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        String hql = "FROM Rating";
        s.createQuery(hql);
        List<Rating> result = new ArrayList<>();
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
    public Rating getBy(Long id) {
        return null;
    }
}
