package com.tuxt.mytest.thread.read_write_lock;
public class WriteTask  extends Thread{
  //logic bean
  private ReadWriteLockLogic readWriteLockOperator;
  //作者
  private String writer;
  public WriteTask(ReadWriteLockLogic readWriteLockOperator, String writer) {
    this.readWriteLockOperator = readWriteLockOperator;
    this.writer = writer;
  }
  private WriteTask(){}
  // 一个很耗时的写任务
  public void run() {
    try {
      while(!isInterrupted()){
        Thread.sleep(100);
        this.readWriteLockOperator.write(this.writer, "hehehhe");
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}