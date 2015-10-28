package cc.sharper.zkClient;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

import java.util.List;


/**
 * Created by liumin3 on 2015/10/27.
 * ZkClient 没什么玩的，但是有些api可以深究一下！
 */
public class Test
{
    public static void main(String[] args)
    {
        //创建节点
        ZkClient zk = new ZkClient("sharper.cc",3000);
//        String path = "/study001/haha";
//        zk.createPersistent(path,true);//可以递归创建
//        zk.create("/study001/haha/haha01","111", CreateMode.PERSISTENT);
//        zk.create("/study001/haha/haha02","222", CreateMode.PERSISTENT);
//        zk.create("/study001/haha/haha03","333", CreateMode.PERSISTENT);
//        zk.create("/study001/haha/haha04","444", CreateMode.PERSISTENT);

        //删除节点
        //zk.delete("/study001/haha/haha01");

        //zk.deleteRecursive("/study001"); //递归删除




        //判断是否存在
       boolean bb =  zk.exists("/study001/haha/haha04");
        System.out.println("isexists:"+bb);


        //修改节点
        zk.writeData("/study001/haha/haha04", "888");



        //查看节点

        List<String> li =  zk.getChildren("/study001/haha");//获取得到是节点的名称的list
        for(String str :li)
        {
            System.out.println(str);
        }
    }

}
