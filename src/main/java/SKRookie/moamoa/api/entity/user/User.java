package SKRookie.moamoa.api.entity.user;

import SKRookie.moamoa.api.entity.join.Join;
import SKRookie.moamoa.api.entity.reply.Reply;
import SKRookie.moamoa.oauth.entity.ProviderType;
import SKRookie.moamoa.oauth.entity.RoleType;
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
    @Size(max = 64)
    private String userId;

    @Column(name = "USERNAME")
    private String username;

    @JsonIgnore
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "EMAIL_VERIFIED_YN", length = 1)
    @NotNull
    @Size(min = 1, max = 1)
    private String emailVerifiedYn;

    @Column(name = "PROFILE_IMAGE_URL")
    private String profileImageUrl;

    @Column(name = "PROVIDER_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ProviderType providerType;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "USER_INFO")
    private String userInfo;

    @Column(name = "ROLE_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleType roleType;

    @Column(name = "CREATED_AT")
    @NotNull
    private LocalDateTime createdAt;

    @Column(name = "MODIFIED_AT")
    @NotNull
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "joinUser")
    private List<Join> userJoins;

    @OneToMany(mappedBy = "replyUser")
    private List<Reply> userReplys;

    public User(
            String userId,
            String name,
            String email,
            String y,
            String imageUrl,
            ProviderType providerType,
            RoleType user,
            LocalDateTime now
    ) {
        this.userId = userId;
        this.username = name;
        this.password = "NO_PASS";
        this.email = email != null ? email : "NO_EMAIL";
        this.emailVerifiedYn = y;
        this.profileImageUrl = imageUrl != null ? imageUrl : "";
        this.providerType = providerType;
        this.roleType = user;
        this.createdAt = now;
    }
}
