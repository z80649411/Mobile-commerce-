package com.rl.ecps.service.impl;

import com.rl.ecps.dao.EbBrandDao;
import com.rl.ecps.dao.TsPtlUserDao;
import com.rl.ecps.dao.impl.TsPtlUserDaoImpl;
import com.rl.ecps.model.EbBrand;
import com.rl.ecps.model.TsPtlUser;
import com.rl.ecps.service.EbBrandService;
import com.rl.ecps.service.TsPtlUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ozc on 2017/8/26.
 */

@Service
public class TsptlUserServiceImpl implements TsPtlUserService {


    @Autowired
    private TsPtlUserDao ptlUserDao;

    public TsPtlUser findUserByUsernamePass(String username,String password) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("username", username);
        map.put("password", password);


        return ptlUserDao.findUserByUsernamePass(map);
    }

}
