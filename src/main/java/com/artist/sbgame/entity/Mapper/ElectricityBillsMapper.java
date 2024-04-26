package com.artist.sbgame.entity.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface ElectricityBillsMapper {
    void insertElectricityBills(@Param("userId") int userId, @Param("electricityBills") BigDecimal electricityBills);

    void deleteRecordFromElectricityBills(@Param("user_id") int userId);
}
