package org.nutz.mvc;

import org.nutz.lang.Stopwatch;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.impl.processor.AbstractProcessor;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yangyang7 on 2017/11/11.
 */
public class LogTimeProcessor extends AbstractProcessor {

    private static final Log log = Logs.get();

    @Override
    public void process(ActionContext ac) throws Throwable {
        Stopwatch sw = Stopwatch.begin();
        try {
            doNext(ac);
        } finally {
            sw.stop();
            if (log.isDebugEnabled()) {
                HttpServletRequest req = ac.getRequest();
                log.debugf("[%4s]URI=%s %sms", req.getMethod(), req.getRequestURI(), sw.getDuration());
            }
        }
    }

}
