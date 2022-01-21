package SKRookie.moamoa.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MateType {
    PROGRESS("진행"),
    REJECT("탈퇴"),
    COMPLETE("완료");

    private String description;

}
