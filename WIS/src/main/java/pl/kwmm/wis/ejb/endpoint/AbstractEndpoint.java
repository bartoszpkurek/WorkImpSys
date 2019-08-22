package pl.kwmm.wis.ejb.endpoint;

import javax.annotation.Resource;
import javax.ejb.SessionContext;

abstract public class AbstractEndpoint {
    
    @Resource
    protected SessionContext sessionContext;
    
}
