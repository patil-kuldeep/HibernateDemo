package school.dao;

import school.model.Board;
import school.model.School;

import java.util.List;

public interface BoardDao extends IBaseDao<Board> {
    List<School> getSchools(int boardId);

}
