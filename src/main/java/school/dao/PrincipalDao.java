package school.dao;

import school.model.Principal;
import school.model.School;

public interface PrincipalDao extends IBaseDao<Principal> {

    int getExperience(int id);

    String getSchoolName(int id);
}
