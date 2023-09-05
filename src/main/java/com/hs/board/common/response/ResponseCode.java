package com.hs.board.common.response;

public interface ResponseCode {
    String Success = "SU";

    String VALIDATE_FAIL = "VF";
    
    String EXISTED_EMAIL = "EE";
    String EXISTED_NINCKNAME = "EN";
    String EXISTED_TEL_NUMBER = "ET";
    
    String NO_EXISTED_USER = "NU";
    String NO_EXISTED_BOARD = "NB";
    String No_PERMISSION = "NP";

    String SIGN_IN_FAIL = "DM";

    String DATABASE_ERROR = "DE";
}
