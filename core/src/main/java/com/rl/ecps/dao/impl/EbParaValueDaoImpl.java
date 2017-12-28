package com.rl.ecps.dao.impl;

import com.rl.ecps.dao.EbParaValueDao;
import com.rl.ecps.model.EbParaValue;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ozc on 2017/9/13.
 */

@Repository
public class EbParaValueDaoImpl extends SqlSessionDaoSupport implements EbParaValueDao {

    private String nameSpace = "com.rl.ecps.sqlMap.EbParaValueMapper.";

    public void saveParaValue(List<EbParaValue> ebParaValues, Long itemId) {

        //获取数据库连接，通过一个连接把数据存到数据库里边去
        SqlSession sqlSession = this.getSqlSession();
        for (EbParaValue ebParaValue : ebParaValues) {
            ebParaValue.setItemId(itemId);
            sqlSession.insert(nameSpace + "insert", ebParaValue);
        }
    }
}
