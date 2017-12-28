package com.rl.ecps.dao.impl;

import com.rl.ecps.dao.EbConsoleLogDao;
import com.rl.ecps.model.EbConsoleLog;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ozc on 2017/8/26.
 */


/**
 * 继承SqlSessionDaoSupport能够得到sessionFactory的引用，非常方便！
 */
@Repository
public class EbConsoleLogDaoImpl extends SqlSessionDaoSupport implements EbConsoleLogDao {

    String nameSpace = "com.rl.ecps.sqlMap.EbConsoleLogMapper.";

    public void saveConsoleLog(EbConsoleLog ConsoleLog) {
        this.getSqlSession().insert(nameSpace + "insert", ConsoleLog);

    }
}
