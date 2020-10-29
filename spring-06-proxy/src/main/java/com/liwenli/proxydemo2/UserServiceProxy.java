package com.liwenli.proxydemo2;

/*
代理角色
 */
public class UserServiceProxy implements UserService {
    private UserServiceImpl userServiceImpl;

//  不建议这样写,spring建议用set()方法对userServiceImpl进行初始化
//    public UserServiceProxy(UserServiceImpl userServiceImpl) {
//        this.userServiceImpl = userServiceImpl;
//    }

    public void setUserServiceImpl(UserServiceImpl userServiceImpl) {//建议用这种方法初始化
        this.userServiceImpl = userServiceImpl;
    }


    @Override
    public void add() {
        log("add");
        userServiceImpl.add();

    }

    @Override
    public void delete() {
        log("delete");
        userServiceImpl.delete();
    }

    @Override
    public void update() {
        log("update");
        userServiceImpl.update();
    }

    @Override
    public void query() {
        log("query");
        userServiceImpl.query();
    }

    /*
        如果要增加需求,则建议在代理角色下增加方法,而不改变原来的代码!!!
     */

    public void log(String msg) {

        System.out.println("使用了"+msg+"方法");
    }
}
