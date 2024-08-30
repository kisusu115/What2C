package com.mop.what2c.oauth2;

public interface OAuth2Response {

    public String getProvider();

    public String getProviderId();

    public String getEmail();
    //사용자 실명 (설정한 이름)
    public String getName();
}
