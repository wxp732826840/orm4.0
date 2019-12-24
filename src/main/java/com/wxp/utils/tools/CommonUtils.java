package com.wxp.utils.tools;

import org.springframework.cglib.beans.BeanCopier;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {
    /**
     * *把request转换成json数据
     **/
    public static String readReqStr(HttpServletRequest request) {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != reader) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * *把request转换成xml数据
     **/
    public static String readReqXml(HttpServletRequest request) {
        String inputLine;
        StringBuffer notityXml = new StringBuffer();
        try {
            while ((inputLine = request.getReader().readLine()) != null) {
                notityXml.append(inputLine);
            }
            request.getReader().close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return notityXml.toString();

    }


    /**
     * *把request转换成map数据
     **/
    public static Map<String, String> getRequestParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            StringBuffer stringBuffer = new StringBuffer();
            //String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                if (i == (values.length - 1)) {
                    stringBuffer.append(values[i]);
                } else {
                    stringBuffer.append(values[i] + ",");
                }
            }
            params.put(name, stringBuffer.toString());
        }
        return params;
    }


    /**
     * 利用正则表达式判断字符串是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }



}
