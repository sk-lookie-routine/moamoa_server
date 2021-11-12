package SKRookie.moamoa.api.entity.user;

import SKRookie.moamoa.api.entity.join.Join;
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

    @Column(name = "USERNAME", length = 100)
    @NotNull
    @Size(max = 100)
    private String username;

    @JsonIgnore
    @Column(name = "PASSWORD", length = 128)
    @NotNull
    @Size(max = 128)
    private String password;

    @Column(name = "EMAIL", length = 512, unique = true)
    @NotNull
    @Size(max = 512)
    private String email;

    @Column(name = "EMAIL_VERIFIED_YN", length = 1)
    @NotNull
    @Size(min = 1, max = 1)
    private String emailVerifiedYn;

    @Column(name = "PROFILE_IMAGE_URL", length = 512)
    @NotNull
    @Size(max = 512)
    private String profileImageUrl;

    @Column(name = "PROVIDER_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ProviderType providerType;

    @Column(name = "IMAGE", length = 512)
    @NotNull
    @Size(max = 512)
    private String image;

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

    public User(
            @NotNull @Size(max = 64) String userId,
            @NotNull @Size(max = 100) String name,
            @NotNull @Size(max = 512) String email,
            @NotNull @Size(max = 1) String y,
            @NotNull @Size(max = 512) String imageUrl,
            @NotNull ProviderType providerType,
            @NotNull RoleType user,
            @NotNull LocalDateTime now
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
