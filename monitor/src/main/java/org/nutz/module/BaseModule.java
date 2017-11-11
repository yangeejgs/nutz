package org.nutz.module;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;

/**
 * Created by yangyang7 on 2017/11/11.
 */
public class BaseModule {

    @Inject
    protected Dao dao;

}
