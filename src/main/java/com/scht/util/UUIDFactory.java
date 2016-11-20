/**
 * @(#) UUIDFactory.java     2013/04/16 16:10
 * <p/>
 * 版权所有 (c) 北京银软网络技术有限公司
 * 北京市海淀区上地国际创业园西区1号
 * 保留所有权利
 */
package com.scht.util;

import java.util.UUID;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author administor
 * @version 1.0
 */
public class UUIDFactory {
    public static String random() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
