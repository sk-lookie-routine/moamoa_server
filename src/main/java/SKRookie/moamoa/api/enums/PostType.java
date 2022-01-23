package SKRookie.moamoa.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostType {
    READY("대기"),
    COMPLETE("완료"),
    DELETED("삭제");

    private String description;
}
