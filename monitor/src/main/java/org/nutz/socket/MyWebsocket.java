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

//    public WsHandler createHandler(Session session, EndpointConfig config) {
//        return new MySimpleWsHandler(); // 是的,返回你自己的实现类就可以了,需要每次新建哦
//    }


}
