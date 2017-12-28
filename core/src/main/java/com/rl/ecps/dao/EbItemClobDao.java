package com.rl.ecps.dao;

import com.rl.ecps.model.EbBrand;
import com.rl.ecps.model.EbItemClob;

import java.util.List;

/**
 * Created by ozc on 2017/8/26.
 */
public interface EbItemClobDao {

    void saveItemClob(EbItemClob ebItemClob, Long itemId);
}
