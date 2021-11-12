package SKRookie.moamoa.api.dto;

import SKRookie.moamoa.api.enums.JoinType;
import lombok.Data;

@Data
public class StudyJoinDto {
    private Long studyJoinSeq;

    private Long studySeq;

    private Long userSeq;

    private JoinType joinType;
}
