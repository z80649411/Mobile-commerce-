package com.rl.ecps.dao.impl;

import com.rl.ecps.dao.EbBrandDao;
import com.rl.ecps.dao.EbShipAddrDao;
import com.rl.ecps.model.EbBrand;
import com.rl.ecps.model.EbShipAddr;
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
public class EbShipAddrDaoImpl extends SqlSessionDaoSupport implements EbShipAddrDao {

    String nameSpace = "com.rl.ecps.sqlMap.EbShipAddrMapper.";

    public List<EbShipAddr> findUserAddress(Long userId) {
        return this.getSqlSession().selectList(nameSpace + "findUserAddress", userId);
    }

    public void insert(EbShipAddr addr) {
        this.getSqlSession().insert(nameSpace + "insert", addr);
    }

    public EbShipAddr selectByPrimaryKey(Long addrId) {
        return this.getSqlSession().selectOne(nameSpace + "selectByPrimaryKey", addrId);
    }

    public void updateByPrimaryKeySelective(EbShipAddr addr) {

        this.getSqlSession().update(nameSpace + "updateByPrimaryKeySelective", addr);
    }

        public void updateDefault(Long addrId) {

            this.getSqlSession().update(nameSpace + "updateDefault", addrId);

        }

        public void deleteDefault(Long userId) {
            this.getSqlSession().update(nameSpace + "deleteDefault", userId);

        }

}
