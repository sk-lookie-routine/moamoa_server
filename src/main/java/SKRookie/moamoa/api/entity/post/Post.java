package SKRookie.moamoa.api.entity.post;

import SKRookie.moamoa.api.entity.join.Join;
import SKRookie.moamoa.api.entity.reply.Reply;
import SKRookie.moamoa.api.entity.user.User;
import SKRookie.moamoa.api.enums.PostType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "POST")
public class Post {
    @JsonIgnore
    @Id
    @Column(name = "POST_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postSeq;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "INFO")
    private String info;

    @Column(name = "GOAL")
    private String goal;

    @Column(name = "HOW")
    private String how;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "MEMBER_COUNT")
    private Long memberCount;

    @Column(name = "CREATED_AT")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @Column(name = "MODIFIED_AT")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifiedAt;

    @Column(name = "DEADLINE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate deadLine;

    @Column(name = "STARTDATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate startDate;

    @Column(name = "ENDDATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate endDate;

    @NotNull
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_SEQ", referencedColumnName = "user_seq")
    private User postUser;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PostType postType;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<String> hashTags = new HashSet<>();

    @OneToMany(mappedBy = "joinPost")
    private List<Join> postJoins;

    @OneToMany(mappedBy = "replyPost")
    private List<Reply> postReplys;
}
