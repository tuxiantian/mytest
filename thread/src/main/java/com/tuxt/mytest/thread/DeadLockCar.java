package com.tuxt.mytest.thread;

import java.util.concurrent.locks.ReentrantLock;

public class DeadLockCar extends Thread{
	protected Object myDirect;
	static ReentrantLock south=new ReentrantLock();
	static ReentrantLock north=new ReentrantLock();
	static ReentrantLock west=new ReentrantLock();
	static ReentrantLock east=new ReentrantLock();
	public DeadLockCar(Object obj) {
		this.myDirect=obj;
		if (myDirect==south) {
			this.setName("south");
		}else if (myDirect==north) {
			this.setName("north");
		}else if (myDirect==west) {
			this.setName("west");
		}else {
			this.setName("east");
		}
	}
	@Override
	public void run() {
		if (myDirect==south) {
			try {
				west.lockInterruptibly();
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				south.lockInterruptibly();
				System.out.println("car to south has passed");
			} catch (InterruptedException e) {
				System.out.println("car to south is killed");
			}finally{
				if (west.isHeldByCurrentThread()) {
					west.unlock();
				}
				if (south.isHeldByCurrentThread()) {
					south.unlock();
				}
			}
		}else if (myDirect==north) {
			try {
				east.lockInterruptibly();
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				north.lockInterruptibly();
				System.out.println("car to north has passed");
			} catch (InterruptedException e) {
				System.out.println("car to north is killed");
			}finally{
				if (west.isHeldByCurrentThread()) {
					west.unlock();
				}
				if (south.isHeldByCurrentThread()) {
					south.unlock();
				}
			}
		}else if (myDirect==west) {
			try {
				north.lockInterruptibly();
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				west.lockInterruptibly();
				System.out.println("car to west has passed");
			} catch (InterruptedException e) {
				System.out.println("car to west is killed");
			}finally{
				if (west.isHeldByCurrentThread()) {
					west.unlock();
				}
				if (north.isHeldByCurrentThread()) {
					north.unlock();
				}
			}
		}else {
			try {
				south.lockInterruptibly();
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				east.lockInterruptibly();
				System.out.println("car to east has passed");
			} catch (InterruptedException e) {
				System.out.println("car to east is killed");
			}finally{
				if (south.isHeldByCurrentThread()) {
					south.unlock();
				}
				if (east.isHeldByCurrentThread()) {
					east.unlock();
				}
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		DeadLockCar car2south=new DeadLockCar(south);
		DeadLockCar car2north=new DeadLockCar(north);
		DeadLockCar car2west=new DeadLockCar(west);
		DeadLockCar car2east=new DeadLockCar(east);
		car2south.start();
		car2north.start();
		car2west.start();
		car2east.start();
		Thread.sleep(1000);
		car2east.interrupt();
	}
}
