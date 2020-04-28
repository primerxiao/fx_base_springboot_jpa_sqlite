package com.primer.dao;

import com.primer.entity.AppConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author HIFeng
 */
@Repository
public interface AppConfigDao extends JpaRepository<AppConfig, String> {

}