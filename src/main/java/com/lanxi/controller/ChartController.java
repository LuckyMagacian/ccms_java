package com.lanxi.controller;

import com.lanxi.entity.Chart;
import com.lanxi.service.ChartService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2016/11/4.
 */
@Controller
@RequestMapping("chart")
public class ChartController {
    private static Logger logger = Logger.getLogger(ChartController.class);

    @Resource
    ChartService chartService;

    @RequestMapping("/chart.do")
    @ResponseBody
    public Map Charts() {
        Map<String, Object> map = new HashMap<>();
        List<Map> mapList = new ArrayList<>();
        try {
            List<Chart> list = chartService.getChartList();
            for (Chart chart : list) {
                Map<String, String> chartMap = new HashMap<>();
                chartMap.put(chart.getChart_id(),chart.getContent());
                mapList.add(chartMap);
            }
            map.put("content", mapList);
            //map=chartService.getChartMap();
            map.put("statusCode", 200);
        } catch (Exception e) {
            map.put("statusCode", 300);
            logger.error("chart.do-geterror" + e.getMessage(), e);
        } finally {
            logger.info("map" + map);
            return map;
        }
    }
}
