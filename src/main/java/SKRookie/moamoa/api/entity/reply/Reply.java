package SKRookie.moamoa.api.entity.reply;

import SKRookie.moamoa.api.entity.study.Study;
import SKRookie.moamoa.api.entity.user.User;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "REPLY")
public class Reply {
    @JsonIgnore
    @Id
    @Column(name = "REPLY_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replySeq;

    @NotNull
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_SEQ", referencedColumnName = "user_seq")
    private User replyUser;

    @NotNull
    @ManyToOne(targetEntity = Study.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "STUDY_SEQ", referencedColumnName = "study_seq")
    private Study replyStudy;

    @Column(name = "CREATED_AT")
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @Column(name = "CONTENT")
    private String content;
}

