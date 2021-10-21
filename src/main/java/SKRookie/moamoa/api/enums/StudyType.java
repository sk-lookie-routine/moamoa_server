package SKRookie.moamoa.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StudyType {
    READY("대기"),
    PROGRESS("진행중"),
    COMPLETE("완료");

    private String description;
}
