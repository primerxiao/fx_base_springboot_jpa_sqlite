package com.primer.service.impl;

import com.primer.entity.AppConfig;
import com.primer.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author HIFeng
 */
@Service
public class BaseServiceImpl<T,ID> implements BaseService<T, ID> {



}
