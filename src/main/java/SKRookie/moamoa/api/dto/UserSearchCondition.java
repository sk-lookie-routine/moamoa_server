package SKRookie.moamoa.api.dto;

import SKRookie.moamoa.api.entity.join.Join;
import SKRookie.moamoa.api.entity.reply.Reply;
import SKRookie.moamoa.api.enums.JoinType;
import SKRookie.moamoa.api.enums.StudyType;
import SKRookie.moamoa.oauth.entity.ProviderType;
import SKRookie.moamoa.oauth.entity.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

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
