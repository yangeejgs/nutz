package org.nutz.test;

import org.nutz.bean.User;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;

import java.util.List;

/**
 * Created by yangyang on 2017/11/10.
 */

@IocBean
@At("/test")
@Ok("json")
@Fail("http:500")
public class TestModule {

    @Inject
    protected Dao dao;

    @At
    public List<User> testConnection(){
        List<User> userList = dao.query(User.class, null);
        return userList;
    }

    @At
    public Integer count(){
        return dao.count(User.class);
    }


}
