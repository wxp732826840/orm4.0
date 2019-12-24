package com.wxp;

import com.wxp.equator.EquatorStrategy;
import com.wxp.equator.FieldInfo;

import java.math.BigDecimal;

public class CustomeEquatorStrategy implements EquatorStrategy {
    @Override
    public boolean setCustomeStrtegy(FieldInfo fieldInfo) {
        if (fieldInfo.getFieldType().equals(BigDecimal.class)) {
            BigDecimal xDec = (BigDecimal) fieldInfo.getFirstVal();
            BigDecimal xDec1 = (BigDecimal) fieldInfo.getSecondVal();
            if (xDec.compareTo(xDec1) == 0) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public int sort() {
        return 10;
    }
}
