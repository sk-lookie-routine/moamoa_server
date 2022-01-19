package SKRookie.moamoa.api.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserSearchCondition {
    private Long userSeq;

    private String userId;

    private String username;
}
