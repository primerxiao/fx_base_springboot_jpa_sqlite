package com.primer.service.impl;

import com.primer.dao.AppConfigDao;
import com.primer.entity.AppConfig;
import com.primer.service.AppConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HIFeng
 */
@Service
public class AppConfigServiceImpl implements AppConfigService {

    @Autowired
    private AppConfigDao appConfigDao;

    @Override
    public AppConfig findByConfigCode(String configCode) {
        return appConfigDao.findById(configCode).orElse(null);
    }
    @Override
    public AppConfig save(AppConfig appConfig) {
        return appConfigDao.save(appConfig);
    }

}
