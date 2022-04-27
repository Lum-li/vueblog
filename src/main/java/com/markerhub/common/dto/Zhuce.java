package com.markerhub.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Zhuce implements Serializable {

    private String username;


    private String password;
    private String repassword;
}
