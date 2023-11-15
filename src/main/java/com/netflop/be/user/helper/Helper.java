package com.netflop.be.user.helper;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Component
@NoArgsConstructor
public class Helper {
    public String DatetimeFormatUTC(Date date ){
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        f.setTimeZone(TimeZone.getTimeZone("UTC"));
        return f.format(date);
    }
}