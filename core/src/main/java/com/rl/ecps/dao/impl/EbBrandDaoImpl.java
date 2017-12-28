package com.rl.ecps.dao.impl;

import com.rl.ecps.dao.EbBrandDao;
import com.rl.ecps.model.EbBrand;
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
public class EbBrandDaoImpl extends SqlSessionDaoSupport implements EbBrandDao {

    String nameSpace = "com.rl.ecps.sqlMap.EbBrandMapper.";

    public void saveBrand(EbBrand brand) {
        this.getSqlSession().insert(nameSpace + "insert", brand);
    }

    public EbBrand getBrandById(Long brandId) {
        return this.getSqlSession().selectOne(nameSpace + "selectByPrimaryKey", brandId);
    }

    public void updateBrand(EbBrand brand) {

        /**
         * updateByPrimaryKeySelective和updata的区别：
         *  一个是动态SQL，一个是静态SQL。这里就使用动态SQL比较好。因为是更新操作
         */
        this.getSqlSession().update(nameSpace + "updateByPrimaryKeySelective", brand);
    }
    public void deleteBrand(Long brandId) {

        this.getSqlSession().delete(nameSpace + "deleteByPrimaryKey", brandId);
    }
    public List<EbBrand> selectBrand() {
        return this.getSqlSession().selectList(nameSpace+"selectBrand");
    }
    public List<EbBrand> selectBrandByName(String brandName) {
        return this.getSqlSession().selectList(nameSpace + "selectBrandByName", brandName);
    }
}
