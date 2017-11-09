package org.nutz;

import org.nutz.bean.User;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import java.util.Date;

/**
 * Created by yangyang on 2017/11/5.
 */
public class MainSetup implements Setup {
    @Override
    public void init(NutConfig nutConfig) {
        Ioc ioc = nutConfig.getIoc();
        Dao dao = ioc.get(Dao.class);
        Daos.createTablesInPackage(dao, "org.nutz.bean", false);

        // 初始化默认根用户
        if (dao.count(User.class) == 0) {
            User user = new User();
            user.setName("admin");
            user.setPassword("admin");
            user.setCreateDateTime(new Date());
            user.setUpdateDateTime(new Date());
            dao.insert(user);
        }
    }

    @Override
    public void destroy(NutConfig nutConfig) {
    }
}
