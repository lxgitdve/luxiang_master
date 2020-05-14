package com.company.project.service.impl;

import com.company.project.dao.sysMapper;
import com.company.project.model.sys;
import com.company.project.service.sysService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by LX on 2020-03-23.
 */
@Service
@Transactional
public class sysServiceImpl extends AbstractService<sys> implements sysService {
    @Resource
    private sysMapper tUserMapper;

}
