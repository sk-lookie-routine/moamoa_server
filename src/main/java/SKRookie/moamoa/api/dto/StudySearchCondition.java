package SKRookie.moamoa.api.dto;

import SKRookie.moamoa.api.enums.StudyType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StudySearchCondition {
    private Long StudySeq;

    private Long userSeq;

    private StudyType studyType;

    private String title;

    private String hashTag;
}
