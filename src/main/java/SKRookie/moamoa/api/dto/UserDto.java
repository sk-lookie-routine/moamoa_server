package SKRookie.moamoa.api.dto;

import SKRookie.moamoa.api.entity.join.Join;
import SKRookie.moamoa.oauth.entity.ProviderType;
import SKRookie.moamoa.oauth.entity.RoleType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDto {
    private Long userSeq;

    private String username;

    private String password;

    private String email;

    private String userId;

    private String emailVerifiedYn;

    private String profileImageUrl;

    private ProviderType providerType;

    private String image;

    private String userInfo;

    private RoleType roleType;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}
