package com.rl.ecps.dao;

import com.rl.ecps.model.EbOrderDetail;
import org.springframework.core.annotation.Order;

/**
 * Created by ozc on 2017/8/26.
 */
public interface EbOrderDetailDao {

    /**
     * 这里保存的并不是List集合，因为考虑到了并发的问题，这里最好使用单个实体
     * 即时一个订单中有多个订单项，这里使用单个实体会方便一点！
     * @param orderDetail
     */
    void saveOrderDetail(EbOrderDetail orderDetail);



}
