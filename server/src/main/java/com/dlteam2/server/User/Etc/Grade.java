package com.dlteam2.server.User.Etc;

public enum Grade {
    BASIC("일반 회원"),
    VIP("VIP 회원")
    ;
    private final String gradeName;
    Grade(String gradeName){
        this.gradeName = gradeName;
    }
    public String gradeName(){
        return gradeName;
    }
}
