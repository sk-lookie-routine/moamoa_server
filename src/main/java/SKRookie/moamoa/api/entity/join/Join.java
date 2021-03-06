package SKRookie.moamoa.api.entity.join;

import SKRookie.moamoa.api.entity.post.Post;
import SKRookie.moamoa.api.entity.study.Study;
import SKRookie.moamoa.api.entity.user.User;
import SKRookie.moamoa.api.enums.JoinType;
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
@Table(name = "JOINS")
public class Join {
    @JsonIgnore
    @Id
    @Column(name = "JOIN_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long joinSeq;

    @NotNull
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.MERGE})
    @JoinColumn(name = "USER_SEQ", referencedColumnName = "user_seq")
    private User joinUser;

    @NotNull
    @ManyToOne(targetEntity = Post.class, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.MERGE})
    @JoinColumn(name = "POST_SEQ", referencedColumnName = "post_seq")
    private Post joinPost;

    @NotNull
    @Enumerated(EnumType.STRING)
    private JoinType joinType;

    @Column(name = "COMMENT")
    private String comment;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
}
