package com.rl.ecps.dao.impl;

import com.rl.ecps.dao.EbBrandDao;
import com.rl.ecps.dao.EbFeatureDao;
import com.rl.ecps.model.EbBrand;
import com.rl.ecps.model.EbFeature;
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
public class EbFeatureDaoImpl extends SqlSessionDaoSupport implements EbFeatureDao {

    String nameSpace = "com.rl.ecps.sqlMap.EbFeatureMapper.";

    public List<EbFeature> selectCommFeature() {
        return this.getSqlSession().selectList(nameSpace + "selectCommFeature");
    }

    public List<EbFeature> selectSpecFeature() {
        return this.getSqlSession().selectList(nameSpace + "selectSpecFeature");
    }

    public List<EbFeature> selectIsSelect() {
        return this.getSqlSession().selectList(nameSpace + "selectIsSelect");
    }
}
