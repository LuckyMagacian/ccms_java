package com.lanxi.service;

import com.lanxi.entity.Chart;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/4.
 */
public interface ChartService {
    //得到chartMap
     Map getChartMap();
     //得到chart列表
     List<Chart> getList();
}
