package com.nik.auth.security.oauth2.client;

import com.nik.auth.security.oauth2.vo.Oauth2LoginVO;

public interface Oauth2Client {

    public Oauth2LoginVO getUser();

}
