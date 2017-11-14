package com.ykaoqin.kit.util;

import com.google.common.collect.Lists;
import com.opencsv.CSVReader;
import com.ykaoqin.kit.holiday.model.HolidayDateProperty;
import com.ykaoqin.kit.holiday.model.HolidayDay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CsvFileUtils {
    private static Logger logger = LoggerFactory.getLogger(CsvFileUtils.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * 返回path/data目录下所有csv文件
     * @param path csv文件根目录
     * @return List[File]
     */
    public static List<File> getAllCsvFiles(String path) {
        List<File> files = Lists.newArrayList();
        File dataFilePath = new File(path, "data");
        if (dataFilePath != null && dataFilePath.exists()) {
            for (File csvFile : dataFilePath.listFiles()) {
                if (csvFile.getName().endsWith(".csv")) {
                    logger.debug("find csv file: {}", csvFile.getName());
                    files.add(csvFile);
                }
            }
        }
        return files;
    }

    /**
     * 返回Csv文件中的所有法定节假日
     * @param csvFile csv文件
     * @return
     */
    public static List<HolidayDay> getHolidayDaysFromCsvFile(File csvFile) {
        List<HolidayDay> holidayDays = Lists.newLinkedList();
        try {
            CSVReader reader = new CSVReader(new FileReader(csvFile));
            reader.iterator().forEachRemaining(e -> {
                HolidayDay holidayDay = new HolidayDay();
                holidayDay.setDay(Date.from(LocalDate.parse(e[0], formatter).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
                holidayDay.setName(e[1]);
                holidayDay.setProp(HolidayDateProperty.valueOf(Integer.valueOf(e[2])));
                holidayDay.setDesc(e[3]);
                holidayDays.add(holidayDay);
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return holidayDays;
    }

}
