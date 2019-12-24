package com.wxp.equator;

public interface EquatorStrategy {

    /**
     * 用来设置自定义的比较策略
     * @param fieldInfo
     * @return
     */
    public boolean setCustomeStrtegy(FieldInfo fieldInfo);

    /**
     * 设置比较策略的顺序
     * @return
     */
    public int sort();
}
