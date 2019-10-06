package com.list.config.service.impl;

import com.list.config.service.ConfigDemoService;
import org.springframework.stereotype.Service;

/**
 * @ClassName ConfigDemoServiceImpl
 * @Description 配置文件服务具体实现类
 * @Author cughu
 * @Date 2019/9/29 23:02
 * @Version v1.0
 **/
@Service
public class ConfigDemoServiceImpl implements ConfigDemoService {

    private String filePath = "/demo.txt";

    private String configContent;



}
