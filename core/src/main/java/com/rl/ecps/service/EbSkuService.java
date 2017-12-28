package com.rl.ecps.service;

import com.rl.ecps.model.EbShipAddr;
import com.rl.ecps.model.EbSku;

import java.util.List;

/**
 * Created by ozc on 2017/8/26.
 */
public interface EbSkuService {
    EbSku selectByPrimaryKey(Long skuId);

    EbSku selectByPrimaryKeyForDetail(Long skuId);


}
