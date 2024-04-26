package com.artist.sbgame.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NonNull
public class ElectricityConsumption {
    private int user_id;
    private BigDecimal user_CurrentElectricityConsumption;
}
