package site.service;

import site.dao.BaseDAO;
import site.dao.HeadLineDAO;
import site.model.HeadLine;
import site.system.web.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HeadLineService extends BaseService<HeadLine> {

    @Autowired
    private HeadLineDAO headLineDAO;

    @Resource(name = "headLineDAO")
    @Override
    public void setDao(BaseDAO<HeadLine> dao) {
        super.setDao(dao);
    }

    /* 获取最新的头条 */
    public Page<HeadLine> listLast(Page<HeadLine> page) {
        Integer totalItem = headLineDAO.countPassed();
        page.setTotalItem(totalItem);
        List<HeadLine> list = headLineDAO.listPageSortByLast(page);
        page.setList(list);
        return page;
    }

    /* 获取最热的前10条 显示在主页 */
    public List<HeadLine> listTopTen() {
        return headLineDAO.listTopTen();
    }

    /* 列出未审核的头条 */
    public List<HeadLine> listUnReview() {
        return headLineDAO.listUnReview();
    }



}
