package com.list.demo.service.impl;

import com.list.demo.entity.dto.ChatReqDTO;
import com.list.demo.entity.vo.IntentAnswerInfoBo;
import com.list.demo.entity.vo.IntentInfoBo;
import com.list.demo.service.DistributeService;
import com.list.plugins.BasePluginService;
import com.list.shaddock.util.GlobalThreadPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName DistributeServiceImpl
 * @Description 任务分发类
 * @Author cughu
 * @Date 2019/8/823:49
 * @Version v1.0
 **/
@Service
public class DistributeServiceImpl implements DistributeService {

    @Autowired
    GlobalThreadPoolUtil globalThreadPoolUtil;

    @Override
    public List<IntentAnswerInfoBo> getAnswer(ChatReqDTO chatReqDTO, List<IntentInfoBo> intentInfoBoList) {

        CountDownLatch countDownLatch = new CountDownLatch(intentInfoBoList.size());

        //反射获取基类实体对象
        List<BasePluginService> basePluginServices = reflectBasePluginServices(chatReqDTO, intentInfoBoList, countDownLatch);
        for (BasePluginService basePluginService : basePluginServices) {
            GlobalThreadPoolUtil.threadPoolExecutor.execute(basePluginService);
        }
        try {
            countDownLatch.await(6000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<IntentAnswerInfoBo> intentAnswerInfoBoList = new ArrayList<>(8);
        for (BasePluginService basePluginService : basePluginServices) {
            intentAnswerInfoBoList.add(basePluginService.getIntentAnswerInfoBo());
        }
        return intentAnswerInfoBoList;
    }


    /**
     * 反射获取基类列表
     *
     * @param chatReqDTO       请求参数
     * @param intentInfoBoList 意图列表
     * @param countDownLatch   栅栏类
     * @return 基类列表
     */
    private List<BasePluginService> reflectBasePluginServices(ChatReqDTO chatReqDTO, List<IntentInfoBo> intentInfoBoList, CountDownLatch countDownLatch) {
        List<BasePluginService> basePluginServices = new ArrayList<>(16);
        for (IntentInfoBo intentInfoBo : intentInfoBoList) {
            try {
                Class tmpClass = Class.forName(intentInfoBo.getIntentBean());
                Constructor[] anns = tmpClass.getDeclaredConstructors();
                Object[] objects = {chatReqDTO, intentInfoBo, countDownLatch};

                //获取合适的构造函数构造任务实例
                Constructor fitConstructor = null;
                for (Constructor constructor : anns) {
                    boolean sameFlag = true;
                    if (constructor.getAnnotatedParameterTypes().length == objects.length) {
                        for (int i = 0; i < objects.length; i++) {
                            if (!objects[i].getClass().equals(constructor.getAnnotatedParameterTypes()[i].getType())) {
                                sameFlag = false;
                                break;
                            }
                        }
                    } else {
                        sameFlag = false;
                    }
                    if (sameFlag) {
                        fitConstructor = constructor;
                        break;
                    }
                }
                //有构造函数就构造实例，不存在则修改栅栏数量
                if(fitConstructor !=null){
                    BasePluginService pluginService = (BasePluginService) tmpClass.getConstructor(fitConstructor.getParameterTypes()).newInstance(objects);
                    basePluginServices.add(pluginService);
                }else{
                    countDownLatch.countDown();
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return basePluginServices;
    }
}
