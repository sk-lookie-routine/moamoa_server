package SKRookie.moamoa.api.dto;

import SKRookie.moamoa.api.enums.StudyType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StudySearchCondition {
    private Long userSeq;

    private StudyType studyType;
}
