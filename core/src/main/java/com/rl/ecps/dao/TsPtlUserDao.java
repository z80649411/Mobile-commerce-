package com.rl.ecps.dao;

import com.rl.ecps.model.EbSku;
import com.rl.ecps.model.TsPtlUser;

import java.util.List;
import java.util.Map;

/**
 * Created by ozc on 2017/8/26.
 */
public interface TsPtlUserDao {

    TsPtlUser findUserByUsernamePass(Map<String,String> stringMap);

}
