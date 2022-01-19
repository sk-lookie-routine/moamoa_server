package SKRookie.moamoa.api.dto;

import SKRookie.moamoa.api.enums.PostType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PostSearchCondition {
    private Long postSeq;

    private Long userSeq;

    private PostType postType;

    private String search;
}
