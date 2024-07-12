package com.umc.common.exception.handler;

import com.umc.common.exception.GeneralException;
import com.umc.common.response.BaseErrorCode;

public class BoardHandler extends GeneralException {

    public BoardHandler(BaseErrorCode code) {
        super(code);
    }
}
