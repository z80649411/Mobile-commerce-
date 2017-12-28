package com.rl.ecps.dao.impl;

import com.rl.ecps.dao.EbItemDao;
import com.rl.ecps.model.EbItem;
import com.rl.ecps.model.QueryCondition;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by ozc on 2017/9/6.
 */

@Repository
public class EbItemDaoImpl extends SqlSessionDaoSupport implements EbItemDao  {
    String nameSpace = "com.rl.ecps.sqlMap.EbItemMapper.";

    public List<EbItem> selectItemByCondition(QueryCondition queryCondition) {
        return this.getSqlSession().selectList(nameSpace + "selectItemByCondition", queryCondition);
    }
    public int selectItemByConditionCount(QueryCondition queryCondition) {
        return this.getSqlSession().selectOne(nameSpace + "selectItemByConditionCount", queryCondition);
    }

    public void saveItem(EbItem ebItem) {
        this.getSqlSession().insert(nameSpace + "insert", ebItem);
    }


    public void updateItem(EbItem ebItem) {
        this.getSqlSession().update(nameSpace + "updateByPrimaryKeySelective", ebItem);
    }

    public List<EbItem> listItem(Map<String, Object> map) {
        return this.getSqlSession().selectList(nameSpace + "listItem", map);
    }

    public EbItem selectItemDetail(Long itemId) {
        return this.getSqlSession().selectOne(nameSpace + "selectItemDetail", itemId);
    }

}
