package SKRookie.moamoa.api.dto;

import SKRookie.moamoa.api.enums.StudyType;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StudySearchCondition {
    private Long StudySeq;

    private Long userSeq;

    private List<StudyType> studyTypeList;

    private String search;
}
