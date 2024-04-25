package com.artist.sbgame.entity.Mapper;

import com.artist.sbgame.entity.ElectricityConsumption;
import com.artist.sbgame.entity.LatestElectricityConsumption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface LatestElectricityConsumptionMapper {
    LatestElectricityConsumption getLatestElectricityConsumption(@Param("user_id") int user_id);

    void updateLatestElectricityConsumption(@Param("user_id") int user_id,
                                            @Param("user_CurrentElectricityConsumption") BigDecimal user_CurrentElectricityConsumption);
}
