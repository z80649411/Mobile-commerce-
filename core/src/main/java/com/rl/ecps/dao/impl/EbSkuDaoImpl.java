package com.rl.ecps.dao.impl;

import com.rl.ecps.dao.EbSkuDao;
import com.rl.ecps.dao.EbSkuDao;
import com.rl.ecps.model.EbSku;
import com.rl.ecps.model.EbSku;
import com.rl.ecps.model.EbSpecValue;
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
public class EbSkuDaoImpl extends SqlSessionDaoSupport implements EbSkuDao {

    String nameSpace = "com.rl.ecps.sqlMap.EbSkuMapper.";
    String nameSpace1 = "com.rl.ecps.sqlMap.EbSpecValueMapper.";


    public void saveEbSku(List<EbSku> ebSkus, Long itemId) {
        SqlSession sqlSession = this.getSqlSession();
        for (EbSku ebSku : ebSkus) {

            //设置商品属性
            ebSku.setItemId(itemId);

            sqlSession.insert(nameSpace+"insert", ebSku);
            List<EbSpecValue> specList = ebSku.getSpecList();
            for (EbSpecValue ebSpecValue : specList) {
                ebSpecValue.setSkuId(ebSku.getSkuId());
                sqlSession.insert(nameSpace1 + "insert", ebSpecValue);
            }


        }
    }

    public EbSku selectByPrimaryKey(Long skuId) {
        return this.getSqlSession().selectOne(nameSpace + "selectByPrimaryKey", skuId);
    }

    public EbSku selectByPrimaryKeyForDetail(Long skuId) {
        return this.getSqlSession().selectOne(nameSpace + "selectByPrimaryKeyForDetail", skuId);
    }

    public int updateStock(Map map) {


        return this.getSqlSession().update(nameSpace + "updateStock", map);
    }


}
