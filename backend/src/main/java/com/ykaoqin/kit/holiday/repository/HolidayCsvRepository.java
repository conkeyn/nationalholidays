package com.ykaoqin.kit.holiday.repository;

import com.ykaoqin.kit.holiday.model.HolidayDay;

import java.util.List;

public interface HolidayCsvRepository {
    /**
     * 返回某个国家某年的全部节假日
     * @param nation
     * @param year
     * @return
     */
    public List<HolidayDay> getHoldiayDays(String nation, Integer year);
}
