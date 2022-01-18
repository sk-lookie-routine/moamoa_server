package SKRookie.moamoa.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JoinType {
        WAIT("대기"),
        APPROVED("승인"),
        REFUSED("거절");

        private String description;

}
