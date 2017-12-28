package com.rl.ecps.dao;

import com.rl.ecps.model.EbBrand;
import com.rl.ecps.model.EbOrder;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * Created by ozc on 2017/8/26.
 */
public interface EbOrderDao {

    void saveOrder(EbOrder order);


}
