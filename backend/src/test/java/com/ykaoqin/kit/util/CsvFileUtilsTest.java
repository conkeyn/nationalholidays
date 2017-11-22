package com.ykaoqin.kit.util;

import com.ykaoqin.kit.holiday.model.HolidayDay;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class CsvFileUtilsTest {
    private static Logger logger = LoggerFactory.getLogger(CsvFileUtilsTest.class);
    private static String filePath = null;

    @BeforeClass
    public static void init() {
        try {
            File appFile = ResourceUtils.getFile("classpath:application.yml");
            logger.info("app file path:"+ appFile.getPath());

            filePath = appFile.getParent();
            logger.info("csv files path:"+ filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAllCsvFiles() {
        List<File> files = CsvFileUtils.getAllCsvFiles(filePath);
        assertNotNull(files);
        assertThat(files.size(), greaterThan(0));
    }

    @Test
    public void testGetHolidayDaysFromCsvFile() {
        List<File> files = CsvFileUtils.getAllCsvFiles(filePath);
        List<HolidayDay> holidayDays = CsvFileUtils.getHolidayDaysFromCsvFile(files.get(0));
        assertNotNull(holidayDays);
        assertThat(holidayDays.size(), greaterThan(0));
    }
}
