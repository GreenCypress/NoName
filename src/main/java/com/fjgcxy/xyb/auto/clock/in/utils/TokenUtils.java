package com.fjgcxy.xyb.auto.clock.in.utils;

import com.fjgcxy.xyb.auto.clock.in.model.constant.Constants;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZWH
 * @date 2022/1/14 11:13
 */
public class TokenUtils {
    public static Integer getUserId(HttpServletRequest request) {
        Object attribute = request.getSession().getAttribute(Constants.TOKEN_USER_ID_KEY);
        if (attribute == null) return null;
        if (!StringUtils.isNumeric(attribute.toString())) return null;
        return Integer.parseInt(attribute.toString());
    }
}
