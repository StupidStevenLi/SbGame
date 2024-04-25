package com.artist.sbgame.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NonNull
@AllArgsConstructor
@NoArgsConstructor
public class MailUser {
    private int user_id;
    private String mail;
}
