package SKRookie.moamoa.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StudyType {
    READY("대기"),
    PROGRESS("진행중"),
    COMPLETE("완료"),
    DELETED("삭제됨");

    private String description;
}
