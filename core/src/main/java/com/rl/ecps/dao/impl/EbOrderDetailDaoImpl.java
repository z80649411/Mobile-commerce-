package com.rl.ecps.dao.impl;

import com.rl.ecps.dao.EbOrderDao;
import com.rl.ecps.dao.EbOrderDetailDao;
import com.rl.ecps.model.EbOrderDetail;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

/**
 * Created by ozc on 2017/8/26.
 */


/**
 * 继承SqlSessionDaoSupport能够得到sessionFactory的引用，非常方便！
        */
@Repository
public class EbOrderDetailDaoImpl extends SqlSessionDaoSupport implements EbOrderDetailDao {
    String nameSpace = "com.rl.ecps.sqlMap.EbOrderDetailMapper.";
    public void saveOrderDetail(EbOrderDetail orderDetail) {
        this.getSqlSession().insert(nameSpace + "insert", orderDetail);
    }
}
