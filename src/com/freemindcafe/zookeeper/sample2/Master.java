package com.freemindcafe.zookeeper.sample2;

import java.io.IOException;
import java.util.Random;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.ConnectionLossException;
import org.apache.zookeeper.KeeperException.NoNodeException;
import org.apache.zookeeper.KeeperException.NodeExistsException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;

public class Master implements Watcher {
	ZooKeeper zk;
	String hostPort;
	private Random random = new Random(this.hashCode());
	String serverId = Long.toString(random.nextLong());
	boolean isLeader = false;

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

	void runForMaster() throws InterruptedException {
		while (true) {
			try {
				zk.create("/master", serverId.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
						CreateMode.EPHEMERAL);
				isLeader = true;
				break;
			} catch (NodeExistsException e) {
				isLeader = false;
				break;
			} 
			catch (KeeperException e) {
			}
			if (checkMaster())
				break;
		}
	}
	
	 // returns true if there is a master
    boolean checkMaster() throws InterruptedException {
        while (true) {
            try {
                Stat stat = new Stat();
                byte data[] = zk.getData("/master", false, stat); 
                isLeader = new String(data).equals(serverId); 
                return true;
            } catch (NoNodeException e) {
                // no master, so try create again
                return false;
            } catch (KeeperException e) {
            }
        }
    }	

	public static void main(String args[]) throws Exception {
		Master m = new Master("103.253.147.92:2181");
		m.startZK();
		m.runForMaster();
		if (m.isLeader) {
			System.out.println("I'm the leader");
			// wait for a bit
			Thread.sleep(60000);
		} else {
			System.out.println("Someone else is the leader");
		}
		m.stopZK();
	}
}
