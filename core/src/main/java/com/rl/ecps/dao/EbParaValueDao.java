package com.rl.ecps.dao;


import com.rl.ecps.model.EbItem;
import com.rl.ecps.model.EbParaValue;
import com.rl.ecps.model.QueryCondition;

import java.util.List;

/**
 * Created by ozc on 2017/8/26.
 */
public interface EbParaValueDao {


    /**
     *由于我们的参数数据是多个的，不想在service做循环而浪费我们的数据库连接
     * 因此就使用了List集合作为参数
     * @param ebParaValues
     * @param itemId
     */
    void saveParaValue(List<EbParaValue> ebParaValues, Long itemId);

}
