package SKRookie.moamoa.api.dto;

import SKRookie.moamoa.api.enums.JoinType;
import SKRookie.moamoa.api.enums.StudyType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class JoinSearchCondition {
    private Long postSeq;

    private Long userSeq;

    private JoinType joinType;
}
