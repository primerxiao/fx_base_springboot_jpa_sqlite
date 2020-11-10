package com.primer.dao;

import com.primer.entity.DbConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author HIFeng
 */
@Repository
public interface DbConfigDao extends JpaRepository<DbConfig, String> {

}