package org.example.repository;

import org.example.model.User;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private SessionFactory sessionFactory;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public User save(User user) {
        Transaction transaction = null;
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        try {
            transaction = s.beginTransaction();
            s.saveOrUpdate(user);
            transaction.commit();
            s.close();
            return user;
        } catch (Exception e){
            if (transaction != null) transaction.rollback();
            logger.error("fail to insert record");
            s.close();
            return null;
        }
    }

    @Override
    public User update(Long id, User newUser) {
        User oldUser = getBy(id);
        BeanUtils.copyProperties(newUser, oldUser);
        return save(oldUser);
    }
//    public User update(User user) {
//        Transaction transaction = null;
////        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session s = sessionFactory.openSession();
//        try {
//            transaction = s.beginTransaction();
//            s.saveOrUpdate(user);
//            transaction.commit();
//            return user;
//        } catch (HibernateException e){
//            if (transaction != null) transaction.rollback();
//            s.close();
//            logger.error("unable to update record", e);
//            return null;
//        }
//    }

    @Override
    public boolean delete(User user) {
        String hql = "DELETE User as u WHERE u.userId = :Id";
        int deletedCount = 0;
        Transaction transaction = null;
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        try {
            transaction = s.beginTransaction();
            Query<User> query = s.createQuery(hql);
            query.setParameter("Id", user.getUserId());
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
    public List<User> getUsers() {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        String hql = "FROM User";
        s.createQuery(hql);
        List<User> result = new ArrayList<>();
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
    public User getBy(Long id) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        String hql = "FROM User u WHERE u.userId=:Id";
        s.createQuery(hql);
        try {
            Query<User> query = s.createQuery(hql);
            query.setParameter("Id", id);
            User result = query.uniqueResult();
            s.close();
            return result;
        } catch (HibernateException e){
            logger.error("session close exception try again", e);
            s.close();
            return null;
        }
    }

    @Override
    public User getUserEagerBy(Long id) {
        String hql = "From User u LEFT JOIN FETCH u.ratings WHERE u.userId=:Id";
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        s.createQuery(hql);
        try {
            Query<User> query = s.createQuery(hql);
            query.setParameter("Id", id);
            User result = query.uniqueResult();
            s.close();
            return result;
        } catch (HibernateException e){
            logger.error("fail to retrieve data record", e);
            s.close();
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        String hql = "FROM User u WHERE u.email=:email";
        s.createQuery(hql);
        try {
            Query<User> query = s.createQuery(hql);
            query.setParameter("email", email);
            User result = query.uniqueResult();
            return result;
        } catch (HibernateException e){
            logger.error("session close exception try again", e);
            return null;
        } finally {
            s.close();
        }
    }

    @Override
    public User getUserByUsername(String username) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        String hql = "FROM User u WHERE u.username=:username";
        s.createQuery(hql);
        try {
            Query<User> query = s.createQuery(hql);
            query.setParameter("username", username);
            User result = query.uniqueResult();
            s.close();
            return result;
        } catch (HibernateException e){
            logger.error("session close exception try again", e);
            s.close();
            return null;
        }
    }

    @Override
    public User getUserByCredentials(String username, String password) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        String hql = "FROM User u WHERE u.username=:username AND u.password=:password";
        s.createQuery(hql);
        try {
            Query<User> query = s.createQuery(hql);
            query.setParameter("username", username);
            query.setParameter("password", password);
            User result = query.uniqueResult();
            return result;
        } catch (HibernateException e){
            logger.error("session close exception try again", e);
            return null;
        } finally {
            s.close();
        }
    }

}
