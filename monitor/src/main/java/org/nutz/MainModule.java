package org.nutz;

import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

/**
 * Created by yangyang7 on 2017/11/9.
 */
@IocBy(type = ComboIocProvider.class, args = {"*js", "ioc/",
        // 这个package下所有带@IocBean注解的类,都会登记上
        "*anno", "org.nutz",
        "*tx", // 事务拦截 aop
        "*async"}) // 异步执行aop
@SetupBy(value = MainSetup.class)
@Modules(scanPackage = true)
public class MainModule {
}
