package top.javaguo.utils.IMRongYun;

import org.junit.Test;
import top.javaguo.utils.GuoDateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {


    @Test
    public void TimeDate() throws ParseException {
      /*  //设置转换的日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //开始时间
        Date startDate = sdf.parse("2019-11-01 10:10:10");
        //结束时间
        Date endDate = sdf.parse("2019-11-07 10:10:10");

        //得到相差的天数 betweenDate
        long betweenDate = (endDate.getTime() - startDate.getTime())/(60*60*24*1000);*/
        int y=GuoDateUtil.getBetweenDays("2019-11-01 10:10:10","2019-11-07 10:10:10");

        //打印控制台相差的天数
        System.out.println(y+"yyy");

    }

}
