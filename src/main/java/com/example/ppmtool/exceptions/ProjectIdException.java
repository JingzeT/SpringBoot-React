/*
 * Copyright 2022 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.example.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author 97679
 * @date 2022/5/24 11:29
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectIdException extends RuntimeException {

    public ProjectIdException(String message) {
        super(message);
    }
}