package SKRookie.moamoa.api.dto;

import SKRookie.moamoa.api.enums.MateType;
import lombok.Data;

@Data
public class MateDto {
    private Long mateSeq;

    private Long studySeq;

    private Long userSeq;

    private MateType mateType;
}
