package com.list.shaddock.util;


import io.netty.util.concurrent.DefaultThreadFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName GlobalThreadPoolUtil
 * @Description 全局执行线程池
 * @Author cughu
 * @Date 2019/8/18 23:24
 * @Version v1.0
 **/
@Component
public class GlobalThreadPoolUtil {

    public static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors(),
            3000,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(),
            new DefaultThreadFactory("global-threadpool"),
            new ThreadPoolExecutor.AbortPolicy());

}
