package site.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * 问题分类
 */
@Entity
@Table(name = "question_category")
public class QuestionCategory {

    private Integer id;
    private String name;    // 分类名称
    private String description; // 分类描述
    private Timestamp submitTime;   // 分类添加时间

    private Set<User> users = new HashSet<>();  // 被那些用户关注

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setSubmitTime(Timestamp submitTime) {
        this.submitTime = submitTime;
    }

    /* 被那些用户关注， 关系由用户维护 */
    @ManyToMany(targetEntity = User.class, mappedBy = "attentionQuestionCategories", fetch = FetchType.LAZY)
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}
