package com.tuxt.mytest.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

public class ScattingAndGather {

	 public static void gather()
	    {
	        ByteBuffer header = ByteBuffer.allocate(10);
	        ByteBuffer body = ByteBuffer.allocate(10);
	        
	        byte [] b1 = "123".getBytes();
	        byte [] b2 = "abc".getBytes();
	        header.put(b1);
	        body.put(b2);
	 
	        ByteBuffer [] buffs = {header, body};
	 
	        try
	        {
	            FileOutputStream os = new FileOutputStream("E:/scattingAndGather.txt");
	            FileChannel channel = os.getChannel();
	            channel.write(buffs);
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
	    }
	public static void main(String[] args) throws IOException {
//		gather();
		FileInputStream inputStream=new FileInputStream(Paths.get("E:/", "scattingAndGather.txt").toFile());
		FileChannel channel =inputStream.getChannel();
		ByteBuffer dst =ByteBuffer.allocate(1024);
		channel.read(dst);
		String string=new String(dst.array());
		System.out.println(string);
	}

}
