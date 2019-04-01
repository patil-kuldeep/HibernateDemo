package school.dao;

import school.model.Parent;

public interface ParentDao extends IBaseDao<Parent> {

    Parent getParentByFirstName(String firstName);
}
