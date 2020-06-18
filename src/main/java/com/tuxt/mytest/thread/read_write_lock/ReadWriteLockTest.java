package com.tuxt.mytest.thread.read_write_lock;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class ReadWriteLockTest {
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    //1 创建一个具有排程功能的线程池
    ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
    //2 读写锁的logic bean
    ReadWriteLockLogic lockOperator = new ReadWriteLockLogic();
    //3 生成一个可执行任务(该任务执行完毕可以返回结果 或者 抛出异常；而Runnable接口的run方法则不行)
    Runnable writeTask1 = new WriteTask(lockOperator, "作者A");
    Runnable writeTask2 = new WriteTask(lockOperator, "作者AA");
    //4 延时0秒后每2秒重复执行writeTask1;
    service.scheduleAtFixedRate(writeTask1, 0, 2, TimeUnit.SECONDS);
    service.scheduleAtFixedRate(writeTask2, 0, 2, TimeUnit.SECONDS);
    //5 创建3个读任务
    Runnable readTask1 = new ReadTask(lockOperator, "读者B");
    Runnable readTask2 = new ReadTask(lockOperator, "读者C");
    Runnable readTask3 = new ReadTask(lockOperator, "读者D");
    //6 延时0秒后每秒执行一次task1;
    service.scheduleAtFixedRate(readTask1, 1, 1, TimeUnit.SECONDS);
    service.scheduleAtFixedRate(readTask2, 2, 1, TimeUnit.SECONDS);
    service.scheduleAtFixedRate(readTask3, 3, 1, TimeUnit.SECONDS);
  
  }
}