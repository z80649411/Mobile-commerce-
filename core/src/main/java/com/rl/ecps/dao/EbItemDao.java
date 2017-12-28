package com.rl.ecps.dao;


import com.rl.ecps.model.EbItem;
import com.rl.ecps.model.QueryCondition;

import java.util.List;
import java.util.Map;

/**
 * Created by ozc on 2017/8/26.
 */
public interface EbItemDao {

    /**
     * 根据条件查询商品
     *
     * @param queryCondition
     * @return
     */
    List<EbItem> selectItemByCondition(QueryCondition queryCondition);

    /**
     * 根据条件查询商品的总数量
     *
     * @param queryCondition
     * @return
     */
    int selectItemByConditionCount(QueryCondition queryCondition);

    /**
     * 保存商品
     *
     * @param ebItem
     */
    void saveItem(EbItem ebItem);

    /**
     * 修改商品
     *
     * @param ebItem
     */
    void updateItem(EbItem ebItem);

    /**
     * 根据价钱、商品、参数查询数据。
     * @param map
     * @return
     */
    List<EbItem> listItem(Map<String, Object> map);

    EbItem selectItemDetail(Long itemId);

}
