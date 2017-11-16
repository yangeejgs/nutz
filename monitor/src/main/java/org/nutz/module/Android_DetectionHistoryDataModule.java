package org.nutz.module;

import org.nutz.bean.Android_DetectionHistoryData;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;
import org.slf4j.Logger;

import java.util.Date;


/**
 * Created by yangyang on 2017/11/12.
 */

@IocBean
@At("/fdfcp/external/")
@Ok("json")
@Fail("http:500")
@Encoding(input = "UTF-8", output = "UTF-8")
public class Android_DetectionHistoryDataModule extends BaseModule {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(Android_DetectionHistoryDataModule.class);

    @At
    @AdaptBy(type = JsonAdaptor.class)
    public NutMap saveDetectionHistoryData(@Param("..") Android_DetectionHistoryData data) {
        LOGGER.info("插入Android_DetectionHistoryData");
        NutMap re = new NutMap();
        try {
            data.setCreateDateTime(new Date());
            data.setUpdateDateTime(new Date());
            dao.insert(data);
            return re.setv("code", 200).setv("info", "success");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return re.setv("code", 400).setv("info", "插入数据失败");
    }


}
