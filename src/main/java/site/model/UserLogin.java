package site.model;

import javax.persistence.*;

/**
 * 用户表附表
 * 用于用户登录使用
 */
@Entity
@Table(name = "user_login")
public class UserLogin {

    private Integer id;
    private String email;   // 登录用户名, 实际上就是 email
    private String password; // 登录密码
    private Integer userSource; // 用户来源. 1. 邮箱用户; 2. QQ; 3: 微信 (2,3暂定不实现)
    private Integer emailValidated;  //  邮箱用户是否经过验证, 0:否; 1:是
    private String emailSign;  // 用户邮箱签名, 唯一约束
    private Integer enabled; // 是否禁用用户 1：启用； 0：禁用
    private User user;  // 关联用户主表

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "user_source")
    public Integer getUserSource() {
        return userSource;
    }

    public void setUserSource(Integer userSource) {
        this.userSource = userSource;
    }

    @Column(name = "email_validated")
    public Integer getEmailValidated() {
        return emailValidated;
    }

    public void setEmailValidated(Integer emailValidated) {
        this.emailValidated = emailValidated;
    }

    @Column(name = "email_sign")
    public String getEmailSign() {
        return emailSign;
    }

    public void setEmailSign(String emailSign) {
        this.emailSign = emailSign;
    }

    @Column(name = "enabled")
    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    /* 可以通过 mappedBy 来维护外键关系, 哪个实体类中定义了外键, 哪个实体类就维护外键关系 */
    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
