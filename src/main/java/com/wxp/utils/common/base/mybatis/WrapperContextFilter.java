package com.wxp.utils.common.base.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Splitter;

import java.util.ArrayList;

public class WrapperContextFilter {
    public static  String VAL_SPLIT = "_OR_";
    public static String KEY_SPLIT = "@";


    public enum MatchType {
        eq, ne, gt, ge, lt, le, between, notBetween, like, notLike, likeLeft, likeRight,
        isNull, isNotNull, in, notIn, or
    }

    public static QueryWrapper buildWrapper(QueryWrapper<?> queryWrapper, String key, Object value) {
        if (key.contains(VAL_SPLIT)) {
            ArrayList<Object> list = new ArrayList<>();
            String[] or_s = key.split(VAL_SPLIT);
            if (String.valueOf(value).contains(VAL_SPLIT)) {
                String[] result = String.valueOf(value).split(VAL_SPLIT);
                for (int i = 0; i < result.length; i++) {
                    list.add(result[i]);
                }
            } else {
                for (int i = 0; i < or_s.length; i++) {
                    list.add(value);
                }
            }
            queryWrapper.or(wrapper -> {
                for (int i = 0; i < list.size() && i < or_s.length; i++) {
                    String[] strings = or_s[i].split(KEY_SPLIT);
                    buildWrapper(wrapper, Enum.valueOf(MatchType.class, strings[0]), strings[1], list.get(i)).or();
                }

            });
        } else {
            String[] strings = key.split(KEY_SPLIT);
            queryWrapper = buildWrapper(queryWrapper, Enum.valueOf(MatchType.class, strings[0]), strings[1], value);
        }

        return queryWrapper;
    }


    static QueryWrapper buildWrapper(QueryWrapper<?> queryWrapper, MatchType matchType, String key, Object value) {
        switch (matchType) {
            case eq:
                queryWrapper.eq(key, value);
                break;
            case ne:
                queryWrapper.ne(key, value);
                break;
            case gt:
                queryWrapper.gt(key, value);
                break;
            case ge:
                queryWrapper.ge(key, value);
                break;
            case lt:
                queryWrapper.lt(key, value);
                break;
            case le:
                queryWrapper.le(key, value);
                break;
            //case between: queryWrapper.between(strings[1], value);break;
            //case notBetween: queryWrapper.ne(strings[1], value);break;
            case like:
                queryWrapper.like(key, value);
                break;
            case notLike:
                queryWrapper.notLike(key, value);
                break;
            case likeLeft:
                queryWrapper.likeLeft(key, value);
                break;
            case likeRight:
                queryWrapper.likeRight(key, value);
                break;
            case isNull:
                queryWrapper.isNull(key);
                break;
            case isNotNull:
                queryWrapper.isNotNull(key);
                break;
            case in:
                queryWrapper.in(key, Splitter.on(",").trimResults().omitEmptyStrings().splitToList(String.valueOf(value)));
                break;
            case notIn:
                queryWrapper.notIn(key, Splitter.on(",").trimResults().omitEmptyStrings().splitToList(String.valueOf(value)));
                break;
        }
        return queryWrapper;
    }


}
