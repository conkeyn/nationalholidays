package com.ykaoqin.kit.holiday.repository;

import com.ykaoqin.kit.App;
import com.ykaoqin.kit.holiday.model.HolidayDay;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class HolidayCsvRepositoryTest {

    @Autowired
    private HolidayCsvRepository holidayCsvRepository;

    @Test
    public void testGetHoldiayDays() {
        List<HolidayDay> holidayDays = holidayCsvRepository.getHoldiayDays("cn", 2017);
        assertNotNull(holidayDays);
    }
}
