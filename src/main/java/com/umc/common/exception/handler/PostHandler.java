package com.umc.common.exception.handler;

import com.umc.common.exception.GeneralException;
import com.umc.common.response.status.ErrorCode;

public class PostHandler extends GeneralException {
    public PostHandler(ErrorCode code) {
        super(code);
    }
}
