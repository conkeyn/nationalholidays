package com.ykaoqin.kit.util;

import com.ykaoqin.kit.holiday.model.HolidayDay;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class CsvFileUtilsTest {
    private static String filePath = null;

    @BeforeClass
    public static void init() {
        File file = new File(CsvFileUtils.class.getResource("/").getPath());
        filePath = file.getParent() + File.separator + "resources";
        System.out.println("filePath："+ filePath);
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
