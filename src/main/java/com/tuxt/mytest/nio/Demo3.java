package com.tuxt.mytest.nio;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

public class Demo3 {

	public static void main(String[] args) throws IOException {
		Selector selector = Selector.open();
		SelectableChannel channel=new SelectableChannel() {
			
			@Override
			protected void implCloseChannel() throws IOException {
			}
			
			@Override
			public int validOps() {
				
				return 0;
			}
			
			@Override
			public SelectionKey register(Selector sel, int ops, Object att) throws ClosedChannelException {
				
				return null;
			}
			
			@Override
			public SelectorProvider provider() {
				
				return null;
			}
			
			@Override
			public SelectionKey keyFor(Selector sel) {
				
				return null;
			}
			
			@Override
			public boolean isRegistered() {
				
				return false;
			}
			
			@Override
			public boolean isBlocking() {
				
				return false;
			}
			
			@Override
			public SelectableChannel configureBlocking(boolean block) throws IOException {
				
				return null;
			}
			
			@Override
			public Object blockingLock() {
				
				return null;
			}
		};
		channel.configureBlocking(false);
		SelectionKey key = channel.register(selector, SelectionKey.OP_READ);
		while(true) {
		  int readyChannels = selector.select();
		  if(readyChannels == 0) continue;
		  Set selectedKeys = selector.selectedKeys();
		  Iterator keyIterator = selectedKeys.iterator();
		  while(keyIterator.hasNext()) {
		    SelectionKey key1 = (SelectionKey) keyIterator.next();
		    if(key1.isAcceptable()) {
		        // a connection was accepted by a ServerSocketChannel.
		    } else if (key1.isConnectable()) {
		        // a connection was established with a remote server.
		    } else if (key1.isReadable()) {
		        // a channel is ready for reading
		    } else if (key1.isWritable()) {
		        // a channel is ready for writing
		    }
		    keyIterator.remove();
		  }
		}

	}
}
