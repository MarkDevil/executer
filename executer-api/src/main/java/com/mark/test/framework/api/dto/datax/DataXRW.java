package com.mark.test.framework.api.dto.datax;

/**
 * Created by mark .
 * Data   : 2017/8/1
 * Author : mark
 * Desc   :
 */

public class DataXRW {
    private DataXReader reader;
    private DataXWriter writer;

    public DataXReader getReader() {
        return reader;
    }

    public void setReader(DataXReader reader) {
        this.reader = reader;
    }

    public DataXWriter getWriter() {
        return writer;
    }

    public void setWriter(DataXWriter writer) {
        this.writer = writer;
    }
}
