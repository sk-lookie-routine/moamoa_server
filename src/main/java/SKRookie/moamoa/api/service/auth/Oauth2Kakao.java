package SKRookie.moamoa.api.service.auth;

import SKRookie.moamoa.api.dto.UserDto;
import SKRookie.moamoa.api.entity.user.User;
import SKRookie.moamoa.api.repository.user.UserRepository;
import SKRookie.moamoa.oauth.entity.UserPrincipal;
import SKRookie.moamoa.oauth.exception.OAuthProviderMissMatchException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


@Service
@RequiredArgsConstructor
public class Oauth2Kakao {
    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final String kakaoOauth2ClinetId = "c63c08657e63a89661f53f6bbf43a349";

    private final String frontendRedirectUrl = "http://moa-moa.kr";


    /**
     * 인가 코드를 이용한 authorization
     * @return
     */
    public AuthorizationKakao callTokenApi(String code) throws Exception {
        String grantType = "authorization_code";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", grantType);
        params.add("client_id", kakaoOauth2ClinetId);
        params.add("redirect_uri", frontendRedirectUrl);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String url = "https://kauth.kakao.com/oauth/token";
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            AuthorizationKakao authorization = objectMapper.readValue(response.getBody(), AuthorizationKakao.class);

            return authorization;
        } catch (RestClientException | JsonProcessingException ex) {
            ex.printStackTrace();
            throw new Exception();
        }
    }


    /**
     * accessToken 을 이용한 유저정보 받기
     * @return
     */
    public String callGetUserByAccessToken(String accessToken) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String url = "https://kapi.kakao.com/v2/user/me";
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            // 값 리턴
            return response.getBody();
        }catch (RestClientException ex) {
            ex.printStackTrace();
            throw new Exception();
        }
    }

//    public void saveUser(JSONObject obj) {
//
//        UserDto userDto = new UserDto();
//        JSONParser jsonParse = new JSONParser();
//        userDto.setUserId((String) obj.get("id"));
//        userDto.setEmail(();
//
//
//        if (savedUser == null) {
//            savedUser = userRepository.save(modelMapper.map(userDto, User.class));
//        }
//
//        return modelMapper.map(savedUser, UserDto.class);
//
//    }


}
