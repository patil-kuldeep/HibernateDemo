package school.dao;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBaseDao <T> {

    Serializable save(T object);

    void saveAll(List<T> objects);

    T get(Serializable attribute);

    List<T> getAll();

    void delete(T object);

    void deleteAll(List<T> objects);

    void update(T object);

    List<T> findAllByAttribute(Map<String, Object> attributeList);

    Session getCurrentSession();
}
