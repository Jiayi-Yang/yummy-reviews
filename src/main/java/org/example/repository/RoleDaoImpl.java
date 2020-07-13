package org.example.repository;

import org.example.model.Role;
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
public class RoleDaoImpl implements RoleDao{
//    @Autowired
//    private SessionFactory sessionFactory;

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public Role save(Role role) {
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        try {
            transaction = s.beginTransaction();
            s.saveOrUpdate(role);
            transaction.commit();
            s.close();
            return role;
        } catch (Exception e){
            if (transaction != null) transaction.rollback();
            logger.error("fail to insert record");
            s.close();
            return null;
        }
    }

    @Override
    public Role update(Role role) {
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        try {
            transaction = s.beginTransaction();
            s.saveOrUpdate(role);
            transaction.commit();
            return role;
        } catch (HibernateException e){
            if (transaction != null) transaction.rollback();
            s.close();
            logger.error("unable to update record", e);
            return null;
        }
    }

    @Override
    public boolean delete(Role role) {
        String hql = "DELETE Role as r WHERE r.id = :id";
        int deletedCount = 0;
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        try {
            transaction = s.beginTransaction();
            Query<Role> query = s.createQuery(hql);
            query.setParameter("id", role.getId());
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
    public List<Role> getRoles() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        String hql = "FROM Role";
        s.createQuery(hql);
        List<Role> result = new ArrayList<>();
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
    public Role getBy(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        String hql = "FROM Role r WHERE r.id=:Id";
        s.createQuery(hql);
        try {
            Query<Role> query = s.createQuery(hql);
            query.setParameter("Id", id);
            Role result = query.uniqueResult();
            s.close();
            return result;
        } catch (HibernateException e) {
            logger.error("session close exception try again", e);
            s.close();
            return null;
        }
    }

    @Override
    public Role getRolebyName(String name) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        String hql = "FROM Role r WHERE r.name=:name";
        s.createQuery(hql);
        try {
            Query<Role> query = s.createQuery(hql);
            query.setParameter("name", name);
            Role result = query.uniqueResult();
            s.close();
            return result;
        } catch (HibernateException e) {
            logger.error("session close exception try again", e);
            s.close();
            return null;
        }
    }
}
