package org.nutz.module;

import com.mysql.jdbc.StringUtils;
import org.nutz.bean.User;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;
import org.nutz.socket.MyWebsocket;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by yangyang7 on 2017/11/11.
 */

@IocBean
@At("/user")
@Ok("json:{locked:'password|salt',ignoreNull:true}")
@Fail("http:500")
public class UserModule extends BaseModule {

    @Inject
    private MyWebsocket myWebsocket;

    @At
    public Integer count() {


        return this.dao.count(User.class);
    }

    @At("/")
    @Ok("jsp:jsp.user.list") // 真实路径是 /WEB-INF/jsp/user/list.jsp
    public void index() {
    }

    @At
    public Boolean login(@Param("username") String name, @Param("password") String password, HttpSession session) {
        User user = this.dao.fetch(User.class, Cnd.where("name", "=", name).and("password", "=", password));
        if (null == user) {
            return false;
        } else {
            session.setAttribute("userId", user.getId());
            return true;
        }
    }

    @At
    @Ok(">>:/")
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @At
    public NutMap add(@Param("..") User user) {

        NutMap re = new NutMap();

        String flag = this.checkUser(user, true);
        if (flag != null) {
            return re.setv("ok", false).setv("msg", flag);
        }

        user.setCreateDateTime(new Date());
        user.setUpdateDateTime(new Date());
        user = dao.insert(user);
        return re.setv("ok", true).setv("data", user);
    }

    @At
    public NutMap update(@Param("..") User user) {
        NutMap re = new NutMap();
        String msg = this.checkUser(user, false);
        if (msg != null) {
            return re.setv("ok", false).setv("msg", msg);
        }
        user.setName(null);
        user.setCreateDateTime(null);
        user.setUpdateDateTime(new Date());
        dao.updateIgnoreNull(user);
        return re.setv("ok", true);
    }

    @At
    public NutMap delete(@Param("id") Integer id, @Attr("userId") Integer userId) {
        if (userId == id) {
            return new NutMap().setv("ok", false).setv("msg", "不能删除当前用户");
        }
        int i = dao.delete(User.class, id);
        if (i == 0) {
            return new NutMap().setv("ok", false).setv("msg", "不能删除当前用户");
        }
        return new NutMap().setv("ok", true);
    }


    private String checkUser(User user, Boolean create) {
        if (null == user) {
            return "用户对象为空";
        }
        if (create) {
            if (StringUtils.isNullOrEmpty(user.getName()) || StringUtils.isNullOrEmpty(user.getPassword())) {
                return "用户名/密码不能为空";
            }
        } else {
            if (StringUtils.isNullOrEmpty(user.getPassword())) {
                return "密码不能为空";
            }
        }

        String password = user.getPassword().trim();
        if (6 > password.length() || 12 < password.length()) {
            return "密码长度错误";
        }
        user.setPassword(password);

        if (create) {
            Integer count = this.dao.count(User.class, Cnd.where("name", "=", user.getName()));
            if (count != 0) {
                return "用户名已存在";
            }
        } else {
            if (user.getId() < 1) {
                return "用户id非法";
            }
        }

        if (null != user.getName()) {
            user.setName(user.getName().trim());
        }
        return null;
    }

    @At
    public QueryResult queryUserByName(@Param("name") String name, @Param("..") Pager pager) {
        Cnd cnd = Strings.isBlank(name) ? null : Cnd.where("name", "like", "%" + name + "%");
        QueryResult qr = new QueryResult();
        qr.setList(dao.query(User.class, cnd, pager));
        pager.setRecordCount(dao.count(User.class, cnd));
        qr.setPager(pager);
        return qr;
    }

}
