package com.dlteam2.server.Common;

import com.dlteam2.server.User.Entity.LoginInfoId;
import com.dlteam2.server.User.Etc.Grade;
import com.dlteam2.server.User.Etc.LoginType;
import com.dlteam2.server.User.Etc.Role;

public class Constants {
    public static final String test_user_1_email = "test@tester.com";
    public static final String test_user_1_mobile = "010-1234-5678";
    public static final Grade test_user_1_grade = Grade.BASIC;
    public static final Role test_user_1_role = Role.ROLE_USER;
    public static final String test_user_1_id = "1";
    public static final LoginInfoId test_user_1_login_info = LoginInfoId.builder().loginType(LoginType.BASIC).build();
}
