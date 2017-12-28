package com.rl.ecps.service;

import com.rl.ecps.model.TsPtlUser;

import java.util.Map;

/**
 * Created by ozc on 2017/9/29.
 */
public interface TsPtlUserService {

    TsPtlUser findUserByUsernamePass(String username,String password);

}
