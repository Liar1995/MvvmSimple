package com.sunmeng.mvvmsimple.utils;

/**
 * Created by sunmeng on 2017/11/15.
 * Email:sunmeng995@gmail.com
 * Description:
 */

public class PageUtils {
    private static final int COUNT_PER_PAGE = 30;

    public static int getPage(int count) {
        int page = count / COUNT_PER_PAGE + 1;
        if (count % COUNT_PER_PAGE > 0)
            page++;
        return page;
    }
}
