package school.dao;

import school.dao.IBaseDao;
import school.model.PTA;
import school.model.Parent;
import school.model.School;

import java.util.List;

public interface PTADao extends IBaseDao<PTA> {
    List<School> getMemberSchools(int ptaId);

    List<Parent> getMemberParents(int ptaId);
}
