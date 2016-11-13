package com.lanxi.dao;

import com.lanxi.entity.Chart;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/11/4.
 */
@Repository
public interface ChartDao {
    //得到char-id列表
    List<String> getIds();
    //得到contents列表
    List<String> getContents();
    //得到chart列表
    List<Chart> getChartList();
}
