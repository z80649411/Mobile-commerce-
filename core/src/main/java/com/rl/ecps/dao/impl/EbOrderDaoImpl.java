package com.rl.ecps.dao.impl;

import com.rl.ecps.dao.EbFeatureDao;
import com.rl.ecps.dao.EbOrderDao;
import com.rl.ecps.model.EbFeature;
import com.rl.ecps.model.EbOrder;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ozc on 2017/8/26.
 */


/**
 * 继承SqlSessionDaoSupport能够得到sessionFactory的引用，非常方便！
        */
@Repository
public class EbOrderDaoImpl extends SqlSessionDaoSupport implements EbOrderDao {

    String nameSpace = "com.rl.ecps.sqlMap.EbOrderMapper.";

    public void saveOrder(EbOrder order) {
        this.getSqlSession().insert(nameSpace + "insert", order);
    }
}
