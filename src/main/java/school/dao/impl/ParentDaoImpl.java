package school.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import school.dao.ParentDao;
import school.model.Parent;

@Component
public class ParentDaoImpl extends BaseDao<Parent> implements ParentDao {
    @Override
    public Parent getParentByFirstName(String firstName) {
        Query q = getCurrentSession().createQuery("from Parent where firstName = :fName");
        q.setParameter("fName", firstName);
        return (Parent)q.list().get(0);
    }
}
