package SKRookie.moamoa.api.dto;

import SKRookie.moamoa.api.enums.JoinType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JoinDto {
    private Long joinSeq;

    private Long postSeq;

    private Long userSeq;

    private JoinType joinType;

    private String comment;

    private LocalDateTime createdAt;
}
