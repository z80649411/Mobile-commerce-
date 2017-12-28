package com.rl.ecps.service.impl;

import com.rl.ecps.dao.EbBrandDao;
import com.rl.ecps.dao.EbSkuDao;
import com.rl.ecps.dao.impl.EbSkuDaoImpl;
import com.rl.ecps.model.EbBrand;
import com.rl.ecps.model.EbSku;
import com.rl.ecps.service.EbBrandService;
import com.rl.ecps.service.EbSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ozc on 2017/8/26.
 */

@Service
public class EbSkuServiceImpl implements EbSkuService {


    @Autowired
    private EbSkuDao skuDao;

    public EbSku selectByPrimaryKey(Long skuId) {
        return skuDao.selectByPrimaryKey(skuId);
    }

    public EbSku selectByPrimaryKeyForDetail(Long skuId) {
        return skuDao.selectByPrimaryKeyForDetail(skuId);
    }

}
