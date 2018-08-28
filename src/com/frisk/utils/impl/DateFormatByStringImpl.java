package com.frisk.utils.impl;

import com.frisk.MovieException;
import com.frisk.utils.DateFormatByString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: frisk
 * Date: 2018/7/27
 * Time: 10:36
 */
public class DateFormatByStringImpl implements DateFormatByString {

    @Override
    public Date format(String date) throws MovieException {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        } catch (ParseException e) {
            throw new MovieException("时间格式输入错误");
        }
    }

}
