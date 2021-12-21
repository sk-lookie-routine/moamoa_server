package SKRookie.moamoa.oauth.info;

import SKRookie.moamoa.oauth.entity.ProviderType;
import SKRookie.moamoa.oauth.info.impl.GoogleOAuth2UserInfo;
import SKRookie.moamoa.oauth.info.impl.NaverOAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(ProviderType providerType, Map<String, Object> attributes) {
        switch (providerType) {
            case GOOGLE: return new GoogleOAuth2UserInfo(attributes);
            default: throw new IllegalArgumentException("Invalid Provider Type.");
        }
    }
}
