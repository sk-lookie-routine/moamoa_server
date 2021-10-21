package SKRookie.moamoa.api.entity.study;

import SKRookie.moamoa.api.entity.user.User;
import SKRookie.moamoa.api.enums.JoinType;
import SKRookie.moamoa.api.enums.StudyType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STUDYJOIN")
public class StudyJoin {
    @JsonIgnore
    @Id
    @Column(name = "STUDYJOIN_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyJoinSeq;

    @NotNull
    @ManyToOne(targetEntity = Study.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "STUDYSEQ", referencedColumnName = "study_seq")
    private Study studyJoin;

    @NotNull
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "USERSEQ", referencedColumnName = "user_seq")
    private User studyJoinUser;

    @NotNull
    @Enumerated(EnumType.STRING)
    private JoinType joinType;
}
