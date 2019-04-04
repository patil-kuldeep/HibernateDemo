package school.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import school.dao.PTADao;
import school.model.PTA;
import school.model.Parent;
import school.model.School;

import java.util.List;

@Component
public class PTADaoImpl extends BaseDao<PTA> implements PTADao {
    @Override
    public List<School> getMemberSchools(int ptaId) {
        Query query = getCurrentSession().createQuery("from PTA where id= :ptaId");
        query.setParameter("ptaId", ptaId);
        PTA pta = (PTA)query.list().get(0);
        return pta.getSchools();
    }

    @Override
    public List<Parent> getMemberParents(int ptaId) {
        Query query = getCurrentSession().createQuery("from PTA where id= :ptaId");
        query.setParameter("ptaId", ptaId);
        PTA pta = (PTA)query.list().get(0);
        return pta.getMembers();
    }
}
