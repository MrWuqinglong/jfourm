package site.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 头条
 */
@Entity
@Table(name = "head_line")
public class HeadLine {

    private Integer id;
    private String title;  // 标题
    private String url;     //连接
    private String description; // 描述
    private Timestamp submitTime;   // 发表时间
    private Integer status; // 0：未通过审核； 1：通过审核

    private User user;  // 谁发布的

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "submit_time", columnDefinition = "timestamp default current_timestamp")
    public Timestamp getSubmitTime() {
        return submitTime;
    }

    @Column(name = "status", columnDefinition = "int default 0")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setSubmitTime(Timestamp submitTime) {
        this.submitTime = submitTime;
    }

    /* 谁发布的头条 */
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
