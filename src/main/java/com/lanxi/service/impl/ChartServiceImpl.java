package com.lanxi.service.impl;

import com.lanxi.dao.ChartDao;
import com.lanxi.entity.Chart;
import com.lanxi.service.ChartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/4.
 */
@Service
public class ChartServiceImpl implements ChartService {

    @Resource
    private ChartDao chartDao;
    //得到chartMap
    @Override
    public Map getChartMap() {
         List<String> idList=chartDao.getIds();
         List<String> contentList=chartDao.getContents();
        Map<String,Object> map=new HashMap<String,Object>();
        for(int i=0;i<idList.size();i++){
            map.put(idList.get(i),contentList.get(i));
        }
        return map;
    }

    @Override
    public List<Chart> getChartList() {
        return chartDao.getChartList();
    }
}
