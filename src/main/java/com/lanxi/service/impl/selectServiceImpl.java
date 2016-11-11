package com.lanxi.service.impl;

import com.lanxi.dao.SelectDao;
import com.lanxi.entity.Select;
import com.lanxi.service.SelectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/11/10.
 */
@Service
public class selectServiceImpl implements SelectService{
    @Resource
    SelectDao selectDao;


    @Override
    public void updateApply(Select select) {
        selectDao.updateApply(select);
    }
}
