package com.mark.test.framework.util.loaddata;

/**
 * Created by mark .
 * Data   : 2018/2/9
 *
 */
public interface IReader {
    <T> T loadData(String filename,Class claz);
    <T> T loadData(String filename);
}
