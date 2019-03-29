package school.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import school.dao.PrincipalDao;
import school.model.Principal;

@Component
public class PrincipalDaoImpl extends BaseDao<Principal> implements PrincipalDao {
    @Override
    public int getExperience(int id) {
        Query query = currentSession().createQuery("select yearsOfExperience from Principal where id: pId");
        query.setParameter("pId", id);
        return (int)query.list().get(0);
    }

    @Override
    public String getSchoolName(int id) {
        Query query = currentSession().createQuery("from Principal where id: pId");
        query.setParameter("pId", id);
        Principal principal = (Principal) query.list().get(0);
        return principal.getSchool().getName();
    }
}
