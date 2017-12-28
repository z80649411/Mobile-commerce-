package com.rl.ecps.dao.impl;

import com.rl.ecps.dao.EbItemClobDao;
import com.rl.ecps.dao.EbItemDao;
import com.rl.ecps.model.EbItem;
import com.rl.ecps.model.EbItemClob;
import com.rl.ecps.model.QueryCondition;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ozc on 2017/9/6.
 */

@Repository
public class EbItemClobDaoImpl extends SqlSessionDaoSupport implements EbItemClobDao {
    String nameSpace = "com.rl.ecps.sqlMap.sqlMap.EbItemClobMapper.";
    public void saveItemClob(EbItemClob ebItemClob, Long itemId) {
        ebItemClob.setItemId(itemId);
        this.getSqlSession().insert(nameSpace + "insert", ebItemClob);
    }
}
