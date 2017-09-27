package com.mark.test.framework.core.service;

import java.util.List;
import java.util.Map;

/**
 * Created by mark .
 * Data   : 2017/7/27
 * Author : mark
 * Desc   :
 */
public interface IDbExecuter {

    boolean runsql(Map connectionDTO,String sql);
    List<Map<String,Object>> query(Map connectionDTO,String sql);
}
