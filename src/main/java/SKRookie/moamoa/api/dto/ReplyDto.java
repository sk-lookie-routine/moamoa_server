package SKRookie.moamoa.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReplyDto {
    private Long replySeq;

    private Long postSeq;

    private Long userSeq;

    private LocalDateTime createdAt;

    private String content;
}
