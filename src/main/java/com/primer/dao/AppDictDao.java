package com.primer.dao;

import com.primer.entity.AppDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author HIFeng
 */
@Repository
public interface AppDictDao extends JpaRepository<AppDict, String> {

    /**
     * 根据groupCode查询所有的字典项
     */
    List<AppDict> findAllByGroupCode(String groupCode);

}