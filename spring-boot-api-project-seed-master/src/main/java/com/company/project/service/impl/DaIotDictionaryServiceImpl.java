package com.company.project.service.impl;

import com.company.project.dao.DaIotDictionaryMapper;
import com.company.project.model.DaIotDictionary;
import com.company.project.service.DaIotDictionaryService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by LX on 2020-04-16.
 */
@Service
@Transactional
public class DaIotDictionaryServiceImpl extends AbstractService<DaIotDictionary> implements DaIotDictionaryService {
    @Resource
    private DaIotDictionaryMapper daIotDictionaryMapper;

}
