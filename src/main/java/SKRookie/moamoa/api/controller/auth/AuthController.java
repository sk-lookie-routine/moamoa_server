package SKRookie.moamoa.api.controller.auth;

import SKRookie.moamoa.api.dto.RejectedUserDto;
import SKRookie.moamoa.api.dto.StudyDto;
import SKRookie.moamoa.api.dto.StudySearchCondition;
import SKRookie.moamoa.api.dto.UserDto;
import SKRookie.moamoa.api.entity.auth.AuthReqModel;
import SKRookie.moamoa.api.entity.user.UserRefreshToken;
import SKRookie.moamoa.api.repository.user.UserRefreshTokenRepository;
import SKRookie.moamoa.api.service.auth.AuthService;
import SKRookie.moamoa.api.service.join.JoinService;
import SKRookie.moamoa.api.service.reply.ReplyService;
import SKRookie.moamoa.api.service.study.StudyService;
import SKRookie.moamoa.api.service.user.RejectedUserService;
import SKRookie.moamoa.api.service.user.UserService;
import SKRookie.moamoa.common.ApiResponse;
import SKRookie.moamoa.config.properties.AppProperties;
import SKRookie.moamoa.oauth.entity.RoleType;
import SKRookie.moamoa.oauth.entity.UserPrincipal;
import SKRookie.moamoa.oauth.token.AuthToken;
import SKRookie.moamoa.oauth.token.AuthTokenProvider;
import SKRookie.moamoa.utils.CookieUtil;
import SKRookie.moamoa.utils.HeaderUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AppProperties appProperties;
    private final AuthTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRefreshTokenRepository userRefreshTokenRepository;
    private final UserService userService;
    private final RejectedUserService rejectedUserService;
    private final StudyService studyService;
    private final JoinService joinService;
    private final ReplyService replyService;
    private final ModelMapper modelMapper;

    private final static long THREE_DAYS_MSEC = 259200000;
    private final static String REFRESH_TOKEN = "refresh_token";

//    @PostMapping("/login")
//    public ApiResponse login(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            @RequestBody AuthReqModel authReqModel
//    ) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        authReqModel.getId(),
//                        authReqModel.getPassword()
//                )
//        );
//
//        String userId = authReqModel.getId();
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        Date now = new Date();
//        AuthToken accessToken = tokenProvider.createAuthToken(
//                userId,
//                ((UserPrincipal) authentication.getPrincipal()).getRoleType().getCode(),
//                new Date(now.getTime() + appProperties.getAuth().getTokenExpiry())
//        );
//
//        long refreshTokenExpiry = appProperties.getAuth().getRefreshTokenExpiry();
//        AuthToken refreshToken = tokenProvider.createAuthToken(
//                appProperties.getAuth().getTokenSecret(),
//                new Date(now.getTime() + refreshTokenExpiry)
//        );
//
//        // userId refresh token 으로 DB 확인
//        UserRefreshToken userRefreshToken = userRefreshTokenRepository.findByUserId(userId);
//        if (userRefreshToken == null) {
//            // 없는 경우 새로 등록
//            userRefreshToken = new UserRefreshToken(userId, refreshToken.getToken());
//            userRefreshTokenRepository.saveAndFlush(userRefreshToken);
//        } else {
//            // DB에 refresh 토큰 업데이트
//            userRefreshToken.setRefreshToken(refreshToken.getToken());
//        }
//
//        int cookieMaxAge = (int) refreshTokenExpiry / 60;
//        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN);
//        CookieUtil.addCookie(response, REFRESH_TOKEN, refreshToken.getToken(), cookieMaxAge);
//
//        return ApiResponse.success("token", accessToken.getToken());
//    }

//    @GetMapping("/refresh")
//    public ApiResponse refreshToken (HttpServletRequest request, HttpServletResponse response) {
//        // access token 확인
//        String accessToken = HeaderUtil.getAccessToken(request);
//        AuthToken authToken = tokenProvider.convertAuthToken(accessToken);
//        if (!authToken.validate()) {
//            return ApiResponse.invalidAccessToken();
//        }
//
//        // expired access token 인지 확인
//        Claims claims = authToken.getExpiredTokenClaims();
//        if (claims == null) {
//            return ApiResponse.notExpiredTokenYet();
//        }
//
//        String userId = claims.getSubject();
//        RoleType roleType = RoleType.of(claims.get("role", String.class));
//
//        // refresh token
//        String refreshToken = CookieUtil.getCookie(request, REFRESH_TOKEN)
//                .map(Cookie::getValue)
//                .orElse((null));
//        AuthToken authRefreshToken = tokenProvider.convertAuthToken(refreshToken);
//
//        if (authRefreshToken.validate()) {
//            return ApiResponse.invalidRefreshToken();
//        }
//
//        // userId refresh token 으로 DB 확인
//        UserRefreshToken userRefreshToken = userRefreshTokenRepository.findByUserIdAndRefreshToken(userId, refreshToken);
//        if (userRefreshToken == null) {
//            return ApiResponse.invalidRefreshToken();
//        }
//
//        Date now = new Date();
//        AuthToken newAccessToken = tokenProvider.createAuthToken(
//                userId,
//                roleType.getCode(),
//                new Date(now.getTime() + appProperties.getAuth().getTokenExpiry())
//        );
//
//        long validTime = authRefreshToken.getTokenClaims().getExpiration().getTime() - now.getTime();
//
//        // refresh 토큰 기간이 3일 이하로 남은 경우, refresh 토큰 갱신
//        if (validTime <= THREE_DAYS_MSEC) {
//            // refresh 토큰 설정
//            long refreshTokenExpiry = appProperties.getAuth().getRefreshTokenExpiry();
//
//            authRefreshToken = tokenProvider.createAuthToken(
//                    appProperties.getAuth().getTokenSecret(),
//                    new Date(now.getTime() + refreshTokenExpiry)
//            );
//
//            // DB에 refresh 토큰 업데이트
//            userRefreshToken.setRefreshToken(authRefreshToken.getToken());
//
//            int cookieMaxAge = (int) refreshTokenExpiry / 60;
//            CookieUtil.deleteCookie(request, response, REFRESH_TOKEN);
//            CookieUtil.addCookie(response, REFRESH_TOKEN, authRefreshToken.getToken(), cookieMaxAge);
//        }
//
//        return ApiResponse.success("token", newAccessToken.getToken());
//    }


    // 탈퇴 회원 아닐 때만
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto) {
        Optional<RejectedUserDto> rejectedUserByEmail = rejectedUserService.getRejectedUserByEmail(userDto.getEmail());
        // 만약 이전에 탈퇴한 회원이라면 해당 이메일로 재가입이 불가능하다.
        if (rejectedUserByEmail.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Optional<UserDto> loginUser = userService.addUser(userDto);
        return loginUser.map(user -> ResponseEntity.status(HttpStatus.OK).body(user)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping ("/reject")
    public ResponseEntity<RejectedUserDto> reject(@RequestBody UserDto userDto) {
        // rejected DB 에 추가
        Optional<RejectedUserDto> savedrejectedUser = rejectedUserService.addRejectedUserByEmail(modelMapper.map(userDto, RejectedUserDto.class));

        // 연관 관계 매핑때문에 부모 엔티티에서 삭제 발생하면 나머지 테이블에서도 다 지워야해.. 개노가다..
        // study 도 연관 관계 매핑 있으니 걔 먼저 지우자
        StudySearchCondition studySearchCondition = new StudySearchCondition();
        studySearchCondition.setUserSeq(userDto.getUserSeq());
        Page<StudyDto> study = studyService.getStudy(studySearchCondition, Pageable.unpaged());

        study.forEach(studyDto -> {
            replyService.deleteReplyByStudySeq(studyDto);
            joinService.deleteJoinByStudySeq(studyDto);
        });
        studyService.deleteStudyByUserSeq(userDto);
        replyService.deleteReplyByUserSeq(userDto);
        joinService.deleteJoinByUserSeq(userDto);

        userService.deleteUser(userDto);

        return savedrejectedUser.map(user -> ResponseEntity.status(HttpStatus.OK).body(user)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
