package com.tuxt.mytest.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Demo {

	public static void main(String[] args) throws FileNotFoundException {
		Path path=Paths.get("E:\\code\\rnms\\src\\main\\webapp\\salecard\\js", "common.js");
		path=Paths.get("E:/", "scattingAndGather.txt");
		RandomAccessFile aFile = new RandomAccessFile(path.toFile(), "rw");
		FileChannel inChannel = aFile.getChannel();

		//create buffer with capacity of 1024 bytes
		ByteBuffer buf = ByteBuffer.allocate(1024);

		int bytesRead = 0;
		try {
			bytesRead = inChannel.read(buf);
		} catch (IOException e) {

			e.printStackTrace();
		} 
		//read into buffer.
		while (bytesRead != -1) {

			buf.flip();  //make buffer ready for read

			while(buf.hasRemaining()){
				System.out.print((char) buf.get()); // read 1 byte at a time
			}

			buf.clear(); //make buffer ready for writing
			try {
				bytesRead = inChannel.read(buf);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			aFile.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

}
