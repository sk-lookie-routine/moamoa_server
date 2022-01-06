package SKRookie.moamoa.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RejectedUserDto {
    private Long userSeq;

    private String username;

    private String email;

    private String userId;

    private LocalDateTime createdAt;
}

