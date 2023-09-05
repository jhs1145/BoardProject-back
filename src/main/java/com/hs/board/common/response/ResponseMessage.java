package com.hs.board.common.response;

public interface ResponseMessage {
    String Success = "Success";

    String VALIDATE_FAIL = "Validate Fail";
    
    String EXISTED_EMAIL = "Existed Email";
    String EXISTED_NINCKNAME = "Existed Nickname";
    String EXISTED_TEL_NUMBER = "Existed TelNumber";
    
    String NO_EXISTED_USER = "No Exsited User";
    String NO_EXISTED_BOARD = "No Exsited Board";
    String No_PERMISSION = "No Permission";

    String SIGN_IN_FAIL = "Sign In Data Mismatch";

    String DATABASE_ERROR = "Database Error";
}
