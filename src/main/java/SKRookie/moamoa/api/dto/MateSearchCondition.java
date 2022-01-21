package SKRookie.moamoa.api.dto;

import SKRookie.moamoa.api.enums.MateType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MateSearchCondition {
    private Long studySeq;

    private Long userSeq;

    private MateType mateType;
}
