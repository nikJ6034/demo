package com.nik.auth.security.oauth2.vo;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter @EqualsAndHashCode(callSuper=false)
public class CustomOauth2User extends DefaultOAuth2User{

    private static final long serialVersionUID = 1L;
    private String clientId;
    private String clientName;

    public CustomOauth2User(Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes,
            String nameAttributeKey, String clientId, String clientName) {
        super(authorities, attributes, nameAttributeKey);
        this.clientId = clientId;
        this.clientName = clientName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: [");
        sb.append(this.getName());
        sb.append("], Granted Authorities: [");
        sb.append(getAuthorities());
        sb.append("], User Attributes: [");
        sb.append(getAttributes());
        sb.append("]");
        return sb.toString();
    }
}
