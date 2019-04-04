package school.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import school.dao.IBaseDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseDao<T> implements IBaseDao<T> {

    private Class<T> entityClass;

    @Autowired
    private SessionFactory sessionFactory;

    public BaseDao() {

        entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public Serializable save(T object) {
        return sessionFactory.getCurrentSession().save(object);
    }

    @Override
    public void saveAll(List<T> objects) {
        for(T object: objects) {
            sessionFactory.getCurrentSession().persist(object);
        }
    }

    @Override
    public T get(Serializable id) {
        T object = (T)sessionFactory.getCurrentSession().get(entityClass, id);
        return object;
    }

    @Override
    public List<T> getAll() {
        List<T> objects = new ArrayList<>();
        Query q = sessionFactory.getCurrentSession().createQuery("from " + entityClass.getName());
        objects = q.list();
        return objects;
    }

    @Override
    public void delete(T object) {
        sessionFactory.getCurrentSession().delete(object);
    }

    @Override
    public void deleteAll(List<T> objects) {
        for(T object : objects) {
            sessionFactory.getCurrentSession().delete(object);
        }
    }

    @Override
    public void update(T object) {
        sessionFactory.getCurrentSession().update(object);
    }

    @Override
    public List<T> findAllByAttribute(Map<String, Object> attributeList) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);

        for(Map.Entry<String, Object> entry: attributeList.entrySet()) {
            criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
        }
        List<T> listOfFilteredEntity = criteria.list();
        return listOfFilteredEntity;
    }

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}

