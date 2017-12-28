package com.rl.ecps.dao;

import com.rl.ecps.model.EbSku;


import java.util.List;
import java.util.Map;

/**
 * Created by ozc on 2017/8/26.
 */
public interface EbSkuDao {

    void saveEbSku(List<EbSku> ebSkus, Long itemId);

    EbSku selectByPrimaryKey(Long skuId);


    EbSku selectByPrimaryKeyForDetail(Long skuId);


    int updateStock(Map map);
}
