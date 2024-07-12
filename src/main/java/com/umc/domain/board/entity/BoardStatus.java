package com.umc.domain.board.entity;

import com.umc.common.exception.handler.BoardHandler;
import com.umc.common.response.status.ErrorCode;

public enum BoardStatus {
    USE, DISABLE;

    public static BoardStatus fromString(String state) {
        try {
            return BoardStatus.valueOf(state.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BoardHandler(ErrorCode.BOARD_NOT_AVAILABLE_STATUS);
        }
    }
}
