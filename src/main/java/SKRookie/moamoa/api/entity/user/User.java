package SKRookie.moamoa.api.entity.user;

import SKRookie.moamoa.api.entity.join.Join;
import SKRookie.moamoa.api.entity.mate.Mate;
import SKRookie.moamoa.api.entity.reply.Reply;
import SKRookie.moamoa.api.enums.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER")
public class User {
    @JsonIgnore
    @Id
    @Column(name = "USER_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;

    @Column(name = "USER_ID", length = 64, unique = true)
    @NotNull
    private String userId;

    @Column(name = "USER_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private UserType userType;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "USER_INFO")
    private String userInfo;

    @OneToMany(mappedBy = "joinUser")
    private List<Join> userJoins;

    @OneToMany(mappedBy = "mateUser")
    private List<Mate> userMate;

    @OneToMany(mappedBy = "replyUser")
    private List<Reply> userReplys;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
}