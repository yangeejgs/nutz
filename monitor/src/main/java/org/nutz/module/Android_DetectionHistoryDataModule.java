package org.nutz.module;

import org.apache.log4j.spi.LoggerFactory;
import org.nutz.bean.Android_DetectionHistoryData;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;

import java.util.logging.Logger;

/**
 * Created by yangyang on 2017/11/12.
 */

@IocBean
@At("/fdfcp/external/")
@Ok("json")
@Fail("http:500")
@Encoding(input="UTF-8",output="UTF-8")
public class Android_DetectionHistoryDataModule extends BaseModule {

    @At
    @AdaptBy(type = JsonAdaptor.class)
    public NutMap saveDetectionHistoryData(@Param("..") Android_DetectionHistoryData data) {
        NutMap re = new NutMap();
        try {
            dao.insert(data);
            return re.setv("code", 200).setv("info","success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re.setv("code", 400).setv("info","插入数据失败");
    }


}
