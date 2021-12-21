package SKRookie.moamoa.api.service.auth;

import SKRookie.moamoa.api.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final Oauth2Kakao oauth2Kakao;

    public void oauth2AuthorizationKakao(String code) throws Exception {
        AuthorizationKakao authorization = oauth2Kakao.callTokenApi(code);
        // String to Json
        JSONParser jsonParse = new JSONParser();
        JSONObject obj =  (JSONObject)jsonParse.parse(oauth2Kakao.callGetUserByAccessToken(authorization.getAccess_token()));
        System.out.println("JsonObject 결과 값 :: " + obj);
        // oauth2Kakao.saveUser(obj);

        // System.out.println("userInfoFromKakao = " + userInfoFromKakao);

    }
}
