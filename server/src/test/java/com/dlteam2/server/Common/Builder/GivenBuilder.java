package com.dlteam2.server.Common.Builder;

import com.dlteam2.server.User.Entity.LoginInfo;
import com.dlteam2.server.User.Entity.User;

public class GivenBuilder {
    private final BuilderSupporter builderSupporter;
    private User user;
    private LoginInfo loginInfo;

    public GivenBuilder(BuilderSupporter builderSupporter){
        this.builderSupporter = builderSupporter;
    }

}
