package com.tuxt.mytest.thread.read_write_lock;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
//读写操作的逻辑
public class ReadWriteLockLogic {
  // 初始化一个 ReadWriteLock
  private ReadWriteLock lock = new ReentrantReadWriteLock();
  //共享资源
  private List<String> shareResources = new ArrayList<String>(0);
  //读
  public String read() {
    // 得到 readLock 并锁定
    Lock readLock = lock.readLock();
    readLock.lock();
    try {
      // 读相对省时，做空循环 大约0.5second
      for(int i=0 ;i<2500000; i++){
        System.out.print("");
      }
      // 做读的工作
      StringBuffer buffer = new StringBuffer();
      for (String shareResource : shareResources) {
        buffer.append(shareResource);
        buffer.append("\t");      
      }
      return buffer.toString();
    } finally {
      readLock.unlock();//一定要保证锁的释放
    }
  }
  //写
  public void write(String writer, String content) {
    // 得到 writeLock 并锁定
    Lock writeLock = lock.writeLock();
    writeLock.lock();
    try {
      System.out.println(writer + " write ===" + Thread.currentThread().toString());
      // 写比较费时，所以做空循环 大约13second
      for(int i=0 ;i<10000000; i++){
        System.out.print("");
        System.out.print("");
      }
    
      // 做写的工作
      int count = shareResources.size();
      for (int i=count; i < count + 1; i++) {
        shareResources.add(content + "_" + i);
      }
    } finally {
      writeLock.unlock();//一定要保证锁的释放
    }
  }
}