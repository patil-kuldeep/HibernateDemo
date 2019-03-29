package school.dao;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao <T>{

    void save(T object);

    void saveAll(List<T> objects);

    T get(Serializable attribute);

    List<T> getAll();

    void delete(T object);

    void deleteAll(List<T> objects);

    void update(T object);
}
