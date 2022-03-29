package com.tuxt.mytest.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Demo2 {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		Path pathFrom=Paths.get("E:/test", "fromFile.txt");
		RandomAccessFile fromFile = new RandomAccessFile(pathFrom.toFile(), "rw");
		FileChannel      fromChannel = fromFile.getChannel();
		Path pathTo=Paths.get("E:/test", "toFile.txt");
		
		RandomAccessFile toFile = new RandomAccessFile(pathTo.toFile(), "rw");
		FileChannel      toChannel = toFile.getChannel();
		
		long position = 0;
		long count = fromChannel.size();
		 
		toChannel.transferFrom( fromChannel,position, count);
	}

}
