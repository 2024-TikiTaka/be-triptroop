package com.tikitaka.triptroop.common.security.type;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return getAttributeValue("id");
    }

    @Override
    public String getImageUrl() {
        return getPropertyValue("profile_image");
    }

    @Override
    public String getEmail() {
        return getAttributeValue("account_email");
    }

    @Override
    public String getName() {
        return getPropertyValue("name");
    }

    @Override
    public String getGender() {
        return getPropertyValue("gender");
    }

    @Override
    public String getBirthYear() {
        return getPropertyValue("birthyear");
    }

    @Override
    public String getBirthDay() {
        return getPropertyValue("birthday");
    }

    @Override
    public String getMobile() {
        return getPropertyValue("mobile");
    }
}