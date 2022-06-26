package com.backend.common.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;

import java.util.List;

/**
 * 代码生成
 */
public class CustomGenerator extends AutoGenerator {

    @Override
    protected List<TableInfo> getAllTableInfoList(ConfigBuilder config) {
        List<TableInfo> tableInfoList = config.getTableInfoList();
        for (TableInfo tableInfo : tableInfoList) {
            String substring = tableInfo.getServiceName().substring(1);
            tableInfo.setServiceName(substring);
        }
        return tableInfoList;
    }

}
