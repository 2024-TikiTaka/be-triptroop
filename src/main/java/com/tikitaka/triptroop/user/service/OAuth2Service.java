package com.tikitaka.triptroop.user.service;


import com.tikitaka.triptroop.common.exception.AuthException;
import com.tikitaka.triptroop.common.security.type.NaverOAuth2UserInfo;
import com.tikitaka.triptroop.common.security.type.OAuth2UserInfo;
import com.tikitaka.triptroop.user.domain.repository.UserRepository;
import com.tikitaka.triptroop.user.domain.type.Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2Service extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        log.info("✅OAuth2Service.loadUser");

        // 소셜 로그인 제공자 (예: Google, Facebook 등)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // 인증 제공자에서 사용자 정보가 포함된 속성 이름
        String userNameAttributeName = userRequest.getClientRegistration()
                                                  .getProviderDetails()
                                                  .getUserInfoEndpoint()
                                                  .getUserNameAttributeName();

        log.info("💬registrationId : {} ", registrationId);
        log.info("💬userNameAttributeName : {} ", userNameAttributeName);

        // OAuth2User의 속성들
        Map<String, Object> attributes = oAuth2User.getAttributes();

        OAuth2UserInfo oAuth2UserInfo = getOAuth2UserInfo(registrationId, attributes);

        // try {
        //     return processOAuth2User(oAuth2UserRequest, oAuth2User);
        // } catch (Exception ex) {
        //     // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
        //     throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        // }
        return oAuth2User;
    }


    // 사용자 정보를 바탕으로 사용자 엔티티 생성 또는 업데이트
    // User user = processOAuth2User(registrationId, userNameAttributeName, attributes);

    // return new DefaultOAuth2User(
    //         Collections.singleton(new SimpleGrantedAuthority(user.getRole())),
    //         attributes,
    //         userNameAttributeName
    // );

    // private CustomUser processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
    //
    //     OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(
    //             userRequest.getClientRegistration().getRegistrationId(),
    //             oAuth2User.getAttributes()
    //     );
    //
    //     if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
    //         throw new AuthException("Email not found from OAuth2 provider");
    //     }
    //
    //     final Optional<User> foundUser = userRepository.findByEmail(oAuth2UserInfo.getEmail());
    //     User user;
    //
    //     if (foundUser.isPresent()) {
    //         user = foundUser.get();
    //
    //         // SocialUser socialUser = SocialUserRepository.
    //         // if (!foundUser.getProvider()
    //         //               .equals(AuthProvider.valueOf(userRequest.getClientRegistration().getRegistrationId()))) {
    //         //     throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
    //         //                                                               foundUser.getProvider() + " account. Please use your " + foundUser.getProvider() +
    //         //                                                               " account to login.");
    //         // }
    //         // user = updateExistingUser(foundUser, oAuth2UserInfo);
    //     } else {
    //         user = registerNewUser(userRequest, oAuth2UserInfo);
    //     }
    //
    //     return new CustomUser(user, oAuth2User.getAttributes());
    // }
    //
    // private User registerNewUser(OAuth2UserRequest userRequest, OAuth2UserInfo oAuth2UserInfo) {
    //     User user = new User();
    //
    //     user.setProvider(Provider.valueOf(userRequest.getClientRegistration().getRegistrationId()));
    //     user.setProviderId(oAuth2UserInfo.getId());
    //     user.setName(oAuth2UserInfo.getName());
    //     user.setEmail(oAuth2UserInfo.getEmail());
    //     user.setImageUrl(oAuth2UserInfo.getImageUrl());
    //     return userRepository.save(user);
    // }
    //
    // private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
    //     existingUser.setName(oAuth2UserInfo.getName());
    //     existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
    //     return userRepository.save(existingUser);
    // }


    private OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {

        if (registrationId.equalsIgnoreCase(Provider.KAKAO.toString())) {
            System.out.println("💎Naver");
            return new NaverOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(Provider.NAVER.toString())) {
            System.out.println("💎Kakao");
            return new NaverOAuth2UserInfo(attributes);
        } else {
            throw new AuthException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
