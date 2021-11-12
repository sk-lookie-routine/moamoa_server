package SKRookie.moamoa.api.dto;

import SKRookie.moamoa.api.enums.JoinType;
import SKRookie.moamoa.api.enums.StudyType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StudyJoinSearchCondition {
    private Long studyJoin;

    private Long studyJoinUser;

    private JoinType joinType;
}
