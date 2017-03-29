package site.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户表主表
 */
@Entity
@Table(name = "user")
public class User {

    private Integer id;
    private String nickName;    // 昵称
    private String headImagePath;   // 头像路径
    private String gender;  // 性别
    private String personSing;  //  个人简介
    private Timestamp birthDay; // 出生日期
    private String mobile;  // 手机号
    private String email;   // 邮箱
    private String currentTown; // 现居城市
    private String personUrl;   // 个人主页
    private Timestamp registerTime; // 注册时间
    private Integer score;  // 个人积分

    // 关注的问题分类
    private Set<QuestionCategory> attentionQuestionCategories = new HashSet<>();
    // 关注的问题
    private Set<Question> attentionQuestions = new HashSet<>();

    // 提问的问题
    private Set<Question> askedQuestions = new HashSet<>();
    // 用户的评论
    private Set<Comment> comments = new HashSet<>();

    // 发布的头条
    private Set<HeadLine> headLines = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "nick_name", nullable = false)
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Column(name = "head_image_path")
    public String getHeadImagePath() {
        return headImagePath;
    }

    public void setHeadImagePath(String headImagePath) {
        this.headImagePath = headImagePath;
    }

    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "person_sign")
    public String getPersonSing() {
        return personSing;
    }

    public void setPersonSing(String personSing) {
        this.personSing = personSing;
    }

    @Column(name = "birthday")
    public Timestamp getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Timestamp birthDay) {
        this.birthDay = birthDay;
    }

    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "current_town")
    public String getCurrentTown() {
        return currentTown;
    }

    public void setCurrentTown(String currentTown) {
        this.currentTown = currentTown;
    }

    @Column(name = "person_url")
    public String getPersonUrl() {
        return personUrl;
    }

    public void setPersonUrl(String personUrl) {
        this.personUrl = personUrl;
    }

    @Column(name = "register_time", columnDefinition = "timestamp default current_timestamp")
    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    /* 关注的问题分类， 生成中间表 */
    @ManyToMany(targetEntity = QuestionCategory.class, fetch = FetchType.LAZY)
    @JoinTable(name = "user_attention_question_category",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "question_category_id")})
    public Set<QuestionCategory> getAttentionQuestionCategories() {
        return attentionQuestionCategories;
    }

    public void setAttentionQuestionCategories(Set<QuestionCategory> attentionQuestionCategories) {
        this.attentionQuestionCategories = attentionQuestionCategories;
    }

    /* 关注的问题， 生成中间表 */
    @ManyToMany(targetEntity = Question.class, fetch = FetchType.LAZY)
    @JoinTable(name = "user_attention_question",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "question__id")})
    public Set<Question> getAttentionQuestions() {
        return attentionQuestions;
    }

    public void setAttentionQuestions(Set<Question> attentionQuestions) {
        this.attentionQuestions = attentionQuestions;
    }

    /* 用户提问的问题 */
    @OneToMany(targetEntity = Question.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public Set<Question> getAskedQuestions() {
        return askedQuestions;
    }

    public void setAskedQuestions(Set<Question> askedQuestions) {
        this.askedQuestions = askedQuestions;
    }

    /* 用户发表的评论 */
    @OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    /* 发布的头条 */
    @OneToMany(targetEntity = HeadLine.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public Set<HeadLine> getHeadLines() {
        return headLines;
    }

    public void setHeadLines(Set<HeadLine> headLines) {
        this.headLines = headLines;
    }
}
