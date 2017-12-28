package com.rl.ecps.dao.impl;

import com.rl.ecps.dao.EbSkuDao;
import com.rl.ecps.dao.TsPtlUserDao;
import com.rl.ecps.model.EbSku;
import com.rl.ecps.model.EbSpecValue;
import com.rl.ecps.model.TsPtlUser;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by ozc on 2017/8/26.
 */


/**
 * 继承SqlSessionDaoSupport能够得到sessionFactory的引用，非常方便！
 */
@Repository
public class TsPtlUserDaoImpl extends SqlSessionDaoSupport implements TsPtlUserDao {

    String nameSpace = "com.rl.ecps.sqlMap.TsPtlUserMapper.";


    public TsPtlUser findUserByUsernamePass(Map<String, String> stringMap) {

        return this.getSqlSession().selectOne(nameSpace + "findUserByUsernamePass", stringMap);
    }
}
