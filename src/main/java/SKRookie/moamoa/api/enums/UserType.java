package SKRookie.moamoa.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {
    NORMAL("정상"),
    REJECT("탈퇴");

    private String description;
}
