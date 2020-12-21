package com.mock.tool;

/**
 * 数据源切换类
 *
 */
public class CustomerContextHolder {
	public static final String DATA_SOURCE_DEFAULT = "dataSource1";
    public static final String DATA_SOURCE_B = "dataSource2";
    public static final String DATA_SOURCE_C = "dataSource3";
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    
    
    public static void setCustomerType(String customerType) {
        contextHolder.set(customerType);
    }
    public static String getCustomerType() {
        return contextHolder.get();
    }
    public static void clearCustomerType() {
        contextHolder.remove();
    }

}
