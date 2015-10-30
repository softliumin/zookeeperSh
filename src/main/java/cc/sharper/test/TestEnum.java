package cc.sharper.test;

/**
 * Created by liumin3 on 2015/10/29.
 */
public enum TestEnum
{
    TestEnum1("11","haha","11"),TestEnum2("22","huhu","22"),TestEnum3("33","heihei","33"),TestEnum4("44","hehe","44");

    private  String id;
    private  String name;
    private  String age;
    private TestEnum(String id,String name,String age)
    {
        this.age = age;
        this.id  = id;
        this.name = name;
    }


    public static TestEnum gee()
    {
        return  TestEnum2;
    }


}
