package org.nutz.bean;

import org.nutz.dao.entity.annotation.Column;

import java.util.Date;

/**
 * Created by yangyang7 on 2017/11/11.
 */
public class BasePojo {

    @Column("ct")
    protected Date createDateTime;

    @Column("ut")
    protected Date updateDateTime;

    @Override
    public String toString() {
        return "BasePojo{" +
                "createDateTime=" + createDateTime +
                ", updateDateTime=" + updateDateTime +
                '}';
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
