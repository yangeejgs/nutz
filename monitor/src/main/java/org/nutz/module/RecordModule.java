package org.nutz.module;

import org.nutz.bean.Record;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;
import org.slf4j.Logger;

import java.util.Date;
import java.util.List;


/**
 * Created by yangyang on 2017/11/12.
 */

@IocBean
@At("/sysaspx")
@Ok("json")
@Fail("http:500")
@Encoding(input = "UTF-8", output = "UTF-8")
public class RecordModule extends BaseModule {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(RecordModule.class);

    @At("/apk")
    @AdaptBy(type = JsonAdaptor.class)
    public NutMap addrecord(@Param("MachineId") String MachineId, @Param("RecordList") List<Record> recordList) {
        LOGGER.info("MachineId : {},插入Record{}", MachineId, recordList);
        NutMap re = new NutMap();
        try {
            for (Record record : recordList) {
                record.setMachineId(MachineId);
                record.setCreateDateTime(new Date());
                record.setUpdateDateTime(new Date());
                LOGGER.info("插入信息为record{}", record);
                Record insertRecird = this.dao.insert(record);
                if (null == insertRecird.getRecID() || insertRecird.equals("")) {
                    LOGGER.info("同步recordId信息recordId:{}", record.getId());
                    Integer id = record.getId();
                    record.setRecID(id + "");
                    this.dao.updateIgnoreNull(record);
                }
            }
            return re.setv("code", 200).setv("info", "200");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return re.setv("code", 400).setv("info", "插入数据失败");
    }


}
