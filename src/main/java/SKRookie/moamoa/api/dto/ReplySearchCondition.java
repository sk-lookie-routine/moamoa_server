package SKRookie.moamoa.api.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ReplySearchCondition{
    private Long studySeq;

    private Long userSeq;

    private String content;
}


