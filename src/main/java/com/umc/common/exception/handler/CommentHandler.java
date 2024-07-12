package com.umc.common.exception.handler;

import com.umc.common.exception.GeneralException;
import com.umc.common.response.status.ErrorCode;

public class CommentHandler extends GeneralException {
    public CommentHandler(ErrorCode code) {
        super(code);
    }
}
