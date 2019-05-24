/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kwmm.wis.ejb.endpoint;

import javax.annotation.Resource;
import javax.ejb.SessionContext;

/**
 *
 * @author Warsztat
 */
abstract public class AbstractEndpoint {
    
    @Resource
    protected SessionContext sessionContext;
    
    
}
