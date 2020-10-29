package com.liwenli.proxydemo2;

/*
客户
 */
public class Client {
    public static void main(String[] args) {

        UserServiceImpl userServiceImpl = new UserServiceImpl();//真实对象

//        代理角色:还不存在
        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler();
        proxyInvocationHandler.setTarget(userServiceImpl);//设置要代理的对象为userServiceImpl
//        Object proxy = proxyInvocationHandler.getProxy();//默认返回的是Objecct对象,这里需要我们自己将Object强转为自己需要的类型接口这里是UserService
        //下面一定要强转为自己需要的抽象接口,这里是UserService.java接口
        UserService proxy =(UserService) proxyInvocationHandler.getProxy();//动态地生成一个proxy代理角色
        proxy.add();
        proxy.query();
        proxy.update();
        proxy.delete();

    }

}
