package com.primer.service;

import com.primer.entity.AppConfig;

import java.util.List;

/**
 * @author HIFeng
 */
public interface AppConfigService {
    AppConfig findByConfigCode(String configCode);

    AppConfig save(AppConfig appConfig);

    List<AppConfig> findAll();
}
