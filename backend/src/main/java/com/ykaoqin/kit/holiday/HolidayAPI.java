package com.ykaoqin.kit.holiday;

import com.ykaoqin.kit.core.ServiceResponse;
import com.ykaoqin.kit.holiday.model.HolidayDay;
import com.ykaoqin.kit.holiday.repository.HolidayCsvRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;

@Api("HolidayAPI")
@RestController
@RequestMapping("/v1/nationalholidays/")
public class HolidayAPI {
    @Autowired
    private HolidayCsvRepository holidayCsvRepository;

    @ApiOperation(value = "根据国别返回法定节假日(每年法定节假日不同的，返回当前年份)")
    @GetMapping("/{nation}")
    ServiceResponse getHolidayDays(@PathVariable("nation") @ApiParam(name = "nation", value = "国别", required = true, example = "cn, tw") String nation) {
        if (nation != null) {
            setLocale(nation);
            List<HolidayDay> holidayDays = holidayCsvRepository.getHoldiayDays(nation, null);
            return new ServiceResponse(200, "OK", holidayDays);
        }
        return new ServiceResponse(404, "国家法定节假日未找到！");
    }

    @ApiOperation(value = "根据国别及年份返回法定节假日")
    @GetMapping("/{nation}/{year}")
    ServiceResponse getHolidayDays(@PathVariable("nation") @ApiParam(name = "nation", value = "国别", required = true, example = "cn, tw") String nation, @PathVariable("year") @ApiParam(name = "year", value = "年份", example = "2016, 2017") Integer year) {
        if (nation != null && year != null) {
            setLocale(nation);
            List<HolidayDay> holidayDays = holidayCsvRepository.getHoldiayDays(nation, year);
            return new ServiceResponse(200, "OK", holidayDays);
        }
        return new ServiceResponse(404, "国家法定节假日未找到！");
    }

    @ApiOperation(value = "根据国别及年份下载国家法定节假日Excel")
    @GetMapping("/{nation}/{year}/download")
    public ResponseEntity<Resource> download(@PathVariable("nation") @ApiParam(name = "nation", value = "国别", required = true, example = "cn, tw") String nation, @PathVariable("year") @ApiParam(name = "year", value = "年份", required = true, example = "2016, 2017") Integer year) {
        ByteArrayOutputStream bos = null;
        String filename = "测试.xlsx";
        try {
            Workbook workbook = createExcel();
            bos = new ByteArrayOutputStream();
            workbook.write(bos);
            workbook.close();

            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("charset", "utf-8");
            //设置下载文件名
            filename = URLEncoder.encode(filename, "UTF-8");
            headers.add("Content-Disposition", "attachment;filename=\"" + filename + "\"");

            Resource resource = new InputStreamResource(new ByteArrayInputStream(bos.toByteArray()));

            return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/x-msdownload")).body(resource);

        } catch (IOException e) {
            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
    }

    private void setLocale(String nation) {
        Locale preferredLocale = null;
        String upperNation = nation.toUpperCase();
        if ("CN".equals(upperNation)) {
            preferredLocale = new Locale("zh", upperNation);
        }
        else if ("US".equals(upperNation)) {
            preferredLocale = new Locale("en", upperNation);
        }
        LocaleContextHolder.setLocale(preferredLocale);
    }

    public static Workbook createExcel() {
        //new HSSFWorkbook() make xls
        //new XSSFWorkbook() make xlsx
        Workbook xlsx = new XSSFWorkbook();

        Sheet sheet = xlsx.createSheet();
        Row row = sheet.createRow(1);
        Cell cell0 = row.createCell(0);
        cell0.setCellValue("0");
        Cell cell1 = row.createCell(1);
        cell1.setCellValue("1");
        Cell cell2 = row.createCell(2);
        cell2.setCellValue("2");

        return xlsx;
    }

    @ApiOperation(value = "一次上传单个国家法定节假日Excel")
    @PostMapping("/upload")
    public void upload(@RequestParam("file") @ApiParam(value = "单个Excel文件", required = true) MultipartFile multipartFile) {

    }

    @ApiOperation(value = "一次上传多个国家法定节假日Excel")
    @PostMapping("/uploads")
    public void uploads(@RequestParam("files") @ApiParam(value = "多个Excel文件", required = true) MultipartFile[] multipartFiles) {

    }
}
