package com.rl.ecps.service.impl;

import com.rl.ecps.dao.EbFeatureDao;
import com.rl.ecps.dao.impl.EbFeatureDaoImpl;
import com.rl.ecps.model.EbFeature;
import com.rl.ecps.service.EbFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ozc on 2017/8/26.
 */

@Service
public class EbFeatureServiceImpl implements EbFeatureService {

    @Autowired
    private EbFeatureDao featureDao;
    public List<EbFeature> selectCommFeature() {
        return featureDao.selectCommFeature();
    }

    public List<EbFeature> selectSpecFeature() {
        return featureDao.selectSpecFeature();

    }

    public List<EbFeature> selectIsSelect() {
        return featureDao.selectIsSelect();
    }

}
