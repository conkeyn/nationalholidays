package com.ykaoqin.kit.holiday.repository;

import com.jarvis.cache.annotation.Cache;
import com.ykaoqin.kit.holiday.model.HolidayDay;
import com.ykaoqin.kit.util.CsvFileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Component
public class HolidayCsvRepositoryImpl implements HolidayCsvRepository {
    public final static char Sep_Underline = '_';

    @Value("${data.path}")
    private String dataPath;

    @Override
    @Cache(expire = 3600, expireExpression = "null == #retVal ? 60: 3600", key = "'holiday-day-' + #args[0] +'-'+ #args[1]")
    public List<HolidayDay> getHoldiayDays(String nation, Integer year) {
        Integer iYear = year;
        if (year == null) {
            Calendar cal = Calendar.getInstance();
            iYear = cal.get(Calendar.YEAR);
        }

        File appFile = new File(dataPath);
        if (appFile != null && appFile.exists()) {
            List<File> files = CsvFileUtils.getAllCsvFiles(appFile.getPath());
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

        return Collections.emptyList();
    }
}
