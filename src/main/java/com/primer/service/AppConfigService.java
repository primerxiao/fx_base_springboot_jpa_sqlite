package com.primer.service;

import com.primer.entity.AppConfig;

/**
 * @author HIFeng
 */
public interface AppConfigService {
    AppConfig findByConfigCode(String configCode);

    AppConfig save(AppConfig appConfig);
}
