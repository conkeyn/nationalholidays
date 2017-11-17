package com.ykaoqin.kit.holiday.repository;

import com.jarvis.cache.annotation.Cache;
import com.ykaoqin.kit.holiday.model.HolidayDay;
import com.ykaoqin.kit.util.CsvFileUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Component
public class HolidayCsvRepositoryImpl implements HolidayCsvRepository {
    public final static char Sep_Underline = '_';

    @Override
    @Cache(expire = 60, expireExpression = "null == #retVal ? 60: 60", key = "'holiday-day-' + #args[0] +'-'+ #args[1]")
    public List<HolidayDay> getHoldiayDays(String nation, Integer year) {
        try {
            Integer iYear = year;
            if (year == null) {
                Calendar cal = Calendar.getInstance();
                iYear = cal.get(Calendar.YEAR);
            }

            File appFile = ResourceUtils.getFile("classpath:application.yml");
            if (appFile != null && appFile.exists()) {
                List<File> files = CsvFileUtils.getAllCsvFiles(appFile.getParent());
                for (File file : files) {
                    String fileName = file.getName().substring(0, file.getName().length() - 4);
                    if (fileName.equalsIgnoreCase(nation + Sep_Underline + iYear)) {
                        return CsvFileUtils.getHolidayDaysFromCsvFile(file);
                    }
                    else if (fileName.equalsIgnoreCase(nation)) {
                        return CsvFileUtils.getHolidayDaysFromCsvFile(file);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
