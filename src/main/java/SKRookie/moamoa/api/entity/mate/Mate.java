package SKRookie.moamoa.api.entity.mate;

import SKRookie.moamoa.api.entity.study.Study;
import SKRookie.moamoa.api.entity.user.User;
import SKRookie.moamoa.api.enums.JoinType;
import SKRookie.moamoa.api.enums.MateType;
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
@Table(name = "MATE")
public class Mate {
    @JsonIgnore
    @Id
    @Column(name = "MATE_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mateSeq;

    @NotNull
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.MERGE})
    @JoinColumn(name = "USER_SEQ", referencedColumnName = "user_seq")
    private User mateUser;

    @NotNull
    @ManyToOne(targetEntity = Study.class, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.MERGE})
    @JoinColumn(name = "STUDY_SEQ", referencedColumnName = "study_seq")
    private Study mateStudy;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MateType mateType;
}
