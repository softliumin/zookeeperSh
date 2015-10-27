package cc.sharper.client.book;

import java.util.concurrent.CountDownLatch;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

// ZooKeeper API创建节点，使用异步(async)接口。
public class ZooKeeper_Create_API_ASync_Usage implements Watcher
{

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception
	{
        
	 ZooKeeper zookeeper = new ZooKeeper("domain1.book.zookeeper:2181",5000,new ZooKeeper_Create_API_ASync_Usage());
	 connectedSemaphore.await();

	/**
	 * cb  注册一个异步回调函数     ctx 上下文信息
	 */

	//void create(String path, byte[] data, List<ACL> acl, CreateMode createMode, StringCallback cb, Object ctx)

	 zookeeper.create("/zk-test-ephemeral-", "".getBytes(), 
	    		Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, 
	    		new IStringCallback(), "I am context.");
	    
	 zookeeper.create("/zk-test-ephemeral-", "".getBytes(), 
	    		Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, 
	    		new IStringCallback(), "I am context.");
	    
	 zookeeper.create("/zk-test-ephemeral-", "".getBytes(), 
	    		Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, 
	    		new IStringCallback(), "I am context.");

	 Thread.sleep( Integer.MAX_VALUE );
    }
//输出如下信息：
//	Create path result: [0, /zk-test-ephemeral-, I am context., real path name: /zk-test-ephemeral-
//	Create path result: [-110, /zk-test-ephemeral-, I am context., real path name: null
//	Create path result: [0, /zk-test-ephemeral-, I am context., real path name: /zk-test-ephemeral-0000000006
    
    public void process(WatchedEvent event)
	{
        if (KeeperState.SyncConnected == event.getState())
		{
            connectedSemaphore.countDown();
        }
    }
}
// AsyncCallback这家伙有七种不同的回调接口

class IStringCallback implements AsyncCallback.StringCallback
{
	/**
	 *
	 * @param rc  0接口调用成功  -4 客户端和服务器断开连接 -110 制定节点已经存在  -112 回话已经过期
 	 * @param path
	 * @param ctx
	 * @param name
	 */
  public void processResult(int rc, String path, Object ctx, String name)
  {
    System.out.println("Create path result: [" + rc + ", " + path + ", "
			+ ctx + ", real path name: " + name);
    }
  }