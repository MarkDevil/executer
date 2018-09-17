package com.mark.test.framework.util;

import net.sf.ehcache.util.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Mark .
 * Data : 2016/7/14
 * Desc :
 */
public class ThreadUtils {
    private static Logger logger = LoggerFactory.getLogger(ThreadUtils.class);
    private static final int PROCESSORS = Runtime.getRuntime().availableProcessors();
//    private static ThreadPoolExecutor threadPoolExecutor = ThreadUtils.getThreadPool();
    private int threadNum;
    private int clientNum;



    public ThreadUtils setThreadNum(int threadNum){
        this.threadNum = threadNum;
        return this;
    }

    public ThreadUtils setClientNum(int clientNum){
        this.clientNum = clientNum;
        return this;
    }

    public void hello(Integer integer,String name){
        logger.info("hello - " + name);
    }


//    public static ThreadPoolExecutor getThreadPoolExecuter(){
//        return threadPoolExecutor;
//    }

//    /**
//     * 创建线程池服务
//     * @author  Mark
//     * @return
//     */
//    private static ThreadPoolExecutor getThreadPool(){
//        return new ThreadPoolExecutor(
//                PROCESSORS, PROCESSORS * 2,
//                30000, TimeUnit.MILLISECONDS,
//                new LinkedBlockingDeque<Runnable>(), new NamedThreadFactory("Executer-"));
//    }



    /**
     * @author Mark
     * 执行线程
     * @param claz      传入所需要的类
     * @param method    传入所需要的方法
     */
    private void executeThread(final Class claz, String method, final Object... params){
        assert claz!=null && method != null;
        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        final List<Runnable> runnables = new ArrayList<>();
        final ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        logger.info("threads num :" + threadNum);
        final Method method1 = ReflectorHelper.getMethod(claz,method);
        logger.info("调用方法名:" + method1.getName() + " - invoke times : " + clientNum);
        final int[] invokeTimes = ThreadUtils.this.getInvokeTimes(threadNum, clientNum);

        for (int i = 0; i< threadNum; i++){
            final int finalI = i;
            final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        logger.info("执行调用次数：{}",invokeTimes[finalI]);
                        for (int j = 0; j < invokeTimes[finalI]; j++){
                            logger.info("invoke-start .....");
                            ReflectionUtils.invokeMethod(
                                    method1,
                                    Class.forName(claz.getName()).newInstance(),
                                    params);
                        }
                    } catch (IllegalAccessException | ClassNotFoundException | InstantiationException e) {
                        e.printStackTrace();
                    }finally {
                        countDownLatch.countDown();

                    }
                }
            };
            runnables.add(runnable);
        }
        invokeProcess(runnables,executorService);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    /**
     * @Author Mark
     * @Date 2016/9/21
     * @param
     * @Description  根据执行次数平均分组
     *
     */
    private int[] getInvokeTimes(int threadnum, int clientnum){
        int miss = clientnum%threadnum;
        int rtime = clientnum/threadnum;
        int[] invokeTimes = new int[threadnum];

        for (int thread = 0; thread< threadnum; thread++ ){
            if (thread< threadnum-1){
                invokeTimes[thread] = rtime;
            }else if (thread == threadnum-1){
                invokeTimes[thread] = rtime + miss;
            }
        }
        logger.info("After split : {}", Arrays.toString(invokeTimes));
        return invokeTimes;
    }

    /**
     * @Author Mark
     * @Date 2016/9/21
     * @param
     * @Description  循环调用任务
     *
     */
    private void invokeProcess(List<Runnable> runnables, ExecutorService executorService){
        for (Runnable runnable:runnables){
            executorService.execute(runnable);
        }
    }

    /**
     * @author mark
     * @param type 根据类型选择关闭策略 0-对应立即关闭  其他为执行完毕
     */
//    private void shutDownThreadPool(String type){
//        if (!threadPoolExecutor.isShutdown()){
//            if ("0".equalsIgnoreCase(type)){
//                threadPoolExecutor.shutdownNow();
//            }
//            threadPoolExecutor.shutdown();
//        }else{
//            logger.info("The thread pool is already shutdown");
//        }
//    }





    public static void main(String[] args) {
        ThreadUtils threadUtils = new ThreadUtils().setThreadNum(3).setClientNum(1000);
        threadUtils.executeThread(ThreadUtils.class,"hello",new Integer("1"),"马铭锋");
//        logger.info("\n 单例对比结果：\n {},\n {}", getThreadPoolExecuter().toString(), getThreadPoolExecuter().toString());
//        getThreadPoolExecuter();
    }


}
