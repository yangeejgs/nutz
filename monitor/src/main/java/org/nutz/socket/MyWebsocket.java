package org.nutz.socket;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.plugins.mvc.websocket.AbstractWsEndpoint;
import org.nutz.plugins.mvc.websocket.NutWsConfigurator;

import javax.websocket.server.ServerEndpoint;

/**
 * Created by yangyang7 on 2017/11/11.
 */

@ServerEndpoint(value = "/websocket", configurator = NutWsConfigurator.class)
@IocBean
public class MyWebsocket extends AbstractWsEndpoint {
}
