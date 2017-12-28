package com.rl.ecps.service.impl;

import com.rl.ecps.dao.EbBrandDao;
import com.rl.ecps.dao.impl.EbBrandDaoImpl;
import com.rl.ecps.model.EbBrand;
import com.rl.ecps.service.EbBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ozc on 2017/8/26.
 */

@Service
public class EbBrandServiceImpl implements EbBrandService {

    @Autowired
    private EbBrandDao brandDao;
    public void saveBrand(EbBrand brand) {
        brandDao.saveBrand(brand);

    }

    public EbBrand getBrandById(Long brandId) {
        return brandDao.getBrandById(brandId);
    }

    public void updateBrand(EbBrand brand) {

        brandDao.updateBrand(brand);

    }

    public void deleteBrand(Long brandId) {

        brandDao.deleteBrand(brandId);

    }

    public List<EbBrand> selectBrand() {
        return brandDao.selectBrand();
    }

    public List<EbBrand> selectBrandByName(String brandName) {
        return brandDao.selectBrandByName(brandName);
    }

}
