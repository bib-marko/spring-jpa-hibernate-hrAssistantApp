package com.bib.hrassistantapp.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String currentDateTime() {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
        return dateFormatter.format(new Date());
    }

    public static Object formatDate(Date createdAt) {
        return new SimpleDateFormat("dd-MM-yyyy").format(createdAt);
    }

}
