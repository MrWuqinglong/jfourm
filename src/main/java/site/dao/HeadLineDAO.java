package site.dao;

import site.model.HeadLine;
import site.system.web.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HeadLineDAO extends BaseDAO<HeadLine> {

    /* 获取最新的头条 */
    public List<HeadLine> listPageSortByLast(Page<HeadLine> page) {
        String hql = "FROM HeadLine WHERE status=1 ORDER BY submitTime DESC";
        return listPage(hql, page.getPageNo(), page.getPageSize());
    }

    /* 获取前 10 条头条 */
    public List<HeadLine> listTopTen() {
        String hql = "FROM HeadLine WHERE status=1 ORDER BY submitTime DESC";
        return listPage(hql, 1, 10);
    }

    /* 列出未审核的头条 */
    public List<HeadLine> listUnReview() {
        String hql = "FROM HeadLine WHERE status=0";
        return listWithCustom(hql);
    }

    /**
     * 统计审核通过的头条数量
     */
    public Integer countPassed() {
        String hql = "SELECT COUNT(*) FROM HeadLine WHERE status=1";
        return countWithCondition(hql);
    }
}
