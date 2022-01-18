package SKRookie.moamoa.api.dto;

import SKRookie.moamoa.api.enums.UserType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long userSeq;

    private String username;

    private String email;

    private String userId;

    private UserType userType;

    private String image;

    private String userInfo;

    private LocalDateTime createdAt;
}
