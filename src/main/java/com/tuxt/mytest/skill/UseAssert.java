package com.tuxt.mytest.skill;

/**
 * 使用断言阻断程序，不需要捕获异常，这里通过断言警示不要传入null值。
 * @author tuxiantian@163.com
 *
 */
public class UseAssert {
	public int getHorizontalDistance(Pointer pointer1,Pointer pointer2) {
		assert pointer1 !=null:"pointer1 should not be null";
		assert pointer2 !=null:"pointer2 should not be null";
		return pointer1.getX()-pointer2.getX();
	}
	public static void main(String[] args) {
		UseAssert useAssert=new UseAssert();
		try {
			useAssert.getHorizontalDistance(new Pointer(1, 2), null);
			//		useAssert.getHorizontalDistance(new Pointer(1, 2), new Pointer(3, 2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
class Pointer{
	private int x;
	private int y;
	public  Pointer(int x,int y) {
		this.setX(x);
		this.setY(y);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}