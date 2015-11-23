package com.freemindcafe.zookeeper.sample1;

import java.io.IOException;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher;

public class Master implements Watcher {
    ZooKeeper zk;
    String hostPort;
    Master(String hostPort) {
        this.hostPort = hostPort; 
    }
    void startZK() throws IOException {
        zk = new ZooKeeper(hostPort, 15000, this); 
    }
    public void process(WatchedEvent e) {
        System.out.println(e); 
    }
    void stopZK() throws Exception { 
    	zk.close(); 
    }
    public static void main(String args[])
        throws Exception {
        Master m = new Master("103.253.147.92:2181");
        m.startZK();
     // wait for a bit
        Thread.sleep(60000);
        m.stopZK();
    }
}
