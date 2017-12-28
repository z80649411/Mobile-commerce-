package com.rl.ecps.service;

import com.rl.ecps.model.EbBrand;
import com.rl.ecps.model.EbShipAddr;

import java.util.List;

/**
 * Created by ozc on 2017/8/26.
 */
public interface EbShipAddrService {
    List<EbShipAddr> findUserAddress(Long userId);
    void insert(EbShipAddr addr);

    EbShipAddr selectByPrimaryKey(Long addrId);

    void updateByPrimaryKeySelective(EbShipAddr addr);

    void updateDefault(Long addrId);
    void deleteDefault(Long userId);
}
