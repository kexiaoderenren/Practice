package com.test.cheng.practice.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * 工具类单元测试
 * Created by gaokuncheng on 2017/2/7.
 */
public class ToolClassUnitTest {

    @Test
    public void testDateFormatTransfer() {
        String result = DateUtils.dateFormatTransfer("00000001");
        Assert.assertEquals("0000-00-01", result);
    }
}
