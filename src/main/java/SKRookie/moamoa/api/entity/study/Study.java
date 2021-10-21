package SKRookie.moamoa.api.entity.study;

import SKRookie.moamoa.api.entity.user.User;
import SKRookie.moamoa.api.enums.StudyType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
@Table(name = "STUDY")
public class Study {
    @JsonIgnore
    @Id
    @Column(name = "STUDY_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studySeq;

    @Column(name = "TITLE", length = 512)
    @NotNull
    @Size(max = 512)
    private String title;

    @Column(name = "INFO", length = 512)
    @NotNull
    @Size(max = 512)
    private String info;

    @Column(name = "GOAL", length = 512)
    @NotNull
    @Size(max = 512)
    private String goal;

    @Column(name = "HOW", length = 512)
    @NotNull
    @Size(max = 512)
    private String how;

    @Column(name = "COMMENT", length = 512)
    @NotNull
    @Size(max = 512)
    private String comment;

    @Column(name = "CREATED_AT")
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @Column(name = "MODIFIED_AT")
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifiedAt;

    @Column(name = "DEADLINE")
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate deadLine;

    @Column(name = "STARTDATE")
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate startDate;

    @Column(name = "ENDDATE")
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate endDate;

    @NotNull
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "USERSEQ", referencedColumnName = "user_seq")
    private User studyUser;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StudyType studyType;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<String> hashTags = new HashSet<>();

    @OneToMany(mappedBy = "studyJoin")
    private List<StudyJoin> studyJoins;
}
