package com.tikitaka.triptroop.common.security.type;

import java.util.Map;

public abstract class OAuth2UserInfo {

    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public abstract String getId();

    public abstract String getImageUrl();

    public abstract String getEmail();

    public abstract String getName();

    public abstract String getGender();

    public abstract String getBirthYear();

    public abstract String getBirthDay();

    public abstract String getMobile();

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    protected String getAttributeValue(String key) {
        Object value = attributes.get(key);
        return value != null ? value.toString() : null;
    }

    protected String getPropertyValue(String key) {
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
        if (properties != null) {
            Object value = properties.get(key);
            return value != null ? value.toString() : null;
        }
        return null;
    }
}