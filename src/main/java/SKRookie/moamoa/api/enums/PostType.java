package SKRookie.moamoa.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostType {
    READY("모집중"),
    COMPLETE("모집완료");

    private String description;
}
