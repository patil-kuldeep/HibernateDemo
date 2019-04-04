package school.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import school.dao.BoardDao;
import school.model.Board;
import school.model.School;

import java.util.List;

@Component
public class BoardDaoImpl extends BaseDao<Board> implements BoardDao {
    @Override
    public List<School> getSchools(int boardId) {
        Query query = getCurrentSession().createQuery("from Board where id: id");
        query.setParameter("id", boardId);
        Board board = (Board)query.list().get(0);
        return board.getSchools();
    }
}
