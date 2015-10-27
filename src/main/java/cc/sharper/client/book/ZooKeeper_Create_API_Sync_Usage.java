package cc.sharper.client.book;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

//ZooKeeper API创建节点，使用同步(sync)接口。
public class ZooKeeper_Create_API_Sync_Usage implements Watcher
{

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception
    {
        ZooKeeper zookeeper = new ZooKeeper("domain1.book.zookeeper:2181",5000,new ZooKeeper_Create_API_Sync_Usage());
        connectedSemaphore.await();

       // String create(String path, byte[] data, List<ACL> acl, CreateMode createMode)

        //Ids.OPEN_ACL_UNSAFE  操作不受任何限制
        //临时节点
        String path1 = zookeeper.create("/zk-test-ephemeral-", "".getBytes(),Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        System.out.println("Success create znode: " + path1);

        //临时顺序节点
        String path2 = zookeeper.create("/zk-test-ephemeral-","".getBytes(),Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("Success create znode: " + path2);

        //输出如下
//        Success create znode: /zk-test-ephemeral-
//        Success create znode: /zk-test-ephemeral-0000000004
    }

    public void process(WatchedEvent event)
    {
        if (KeeperState.SyncConnected == event.getState())
        {
            connectedSemaphore.countDown();
        }
    }
}
