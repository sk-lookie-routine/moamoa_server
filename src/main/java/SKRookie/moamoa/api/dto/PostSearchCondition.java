package SKRookie.moamoa.api.dto;

import SKRookie.moamoa.api.enums.PostType;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PostSearchCondition {
    private Long postSeq;

    private Long userSeq;

    private List<PostType> postTypeList;

    private String search;
}
