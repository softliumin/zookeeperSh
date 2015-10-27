package cc.sharper.Curator.book;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

//使用curator来创建一个ZooKeeper客户端
public class Create_Session_Sample
{
    public static void main(String[] args) throws Exception
    {
        /**
         * 初始sleep时间 最大重试次数  最大sleep时间
         */
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

        CuratorFramework client =
        CuratorFrameworkFactory.newClient("domain1.book.zookeeper:2181",
        		5000,
        		3000,
        		retryPolicy);


        client.start();//完成会话的创建
        Thread.sleep(Integer.MAX_VALUE);
    }
}