package com.rl.ecps.service.impl;

import com.rl.ecps.dao.EbBrandDao;
import com.rl.ecps.dao.EbShipAddrDao;
import com.rl.ecps.dao.impl.EbShipAddrDaoImpl;
import com.rl.ecps.model.EbBrand;
import com.rl.ecps.model.EbShipAddr;
import com.rl.ecps.service.EbBrandService;
import com.rl.ecps.service.EbShipAddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ozc on 2017/8/26.
 */

@Service
public class EbShipAddrServiceImpl implements EbShipAddrService {


    @Autowired
    private EbShipAddrDao addrDao;

    public List<EbShipAddr> findUserAddress(Long userId) {
        return addrDao.findUserAddress(userId);
    }

    public void insert(EbShipAddr addr) {
        addrDao.insert(addr);
    }

    public EbShipAddr selectByPrimaryKey(Long addrId) {
        return addrDao.selectByPrimaryKey(addrId);
    }

    public void updateByPrimaryKeySelective(EbShipAddr addr) {

        addrDao.updateByPrimaryKeySelective(addr);
    }

    public void updateDefault(Long addrId) {
        addrDao.updateDefault(addrId);

    }

    public void deleteDefault(Long userId) {
        addrDao.deleteDefault(userId);

    }

}
