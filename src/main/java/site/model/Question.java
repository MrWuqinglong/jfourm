package site.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * 问题类
 */
@Entity
@Table(name = "question")
public class Question {

    private Integer id;
    private String title;   //  问题标题
    private String content; // 问题内容
    private Timestamp submitTime;   // 问题提交时间
    private Integer questionState; // 问题状态: 1:开放; 0:关闭
    private Integer answerNum;   //  回答数
    private Integer attentionNum;   // 关注数

    private User user;  // 提问者
    private QuestionCategory category;  // 问题分类

    private Set<User> users = new HashSet<>(); // 被哪些人关注
    private Set<Comment> comments = new HashSet<>();    //　评论

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

    @Column(name = "content", columnDefinition = "TEXT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "submit_time", columnDefinition = "timestamp default current_timestamp")
    public Timestamp getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Timestamp submitTime) {
        this.submitTime = submitTime;
    }

    @Column(name = "question_state")
    public Integer getQuestionState() {
        return questionState;
    }

    public void setQuestionState(Integer questionState) {
        this.questionState = questionState;
    }

    @Column(name = "answer_num")
    public Integer getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(Integer answerNum) {
        this.answerNum = answerNum;
    }

    @Column(name = "attention_num")
    public Integer getAttentionNum() {
        return attentionNum;
    }

    public void setAttentionNum(Integer attentionNum) {
        this.attentionNum = attentionNum;
    }

    /* w问题分类 */
    @ManyToOne(targetEntity = QuestionCategory.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    public QuestionCategory getCategory() {
        return category;
    }

    public void setCategory(QuestionCategory category) {
        this.category = category;
    }

    /* 问题提出者 */
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /* 用户关注的问题中间表 */
    @ManyToMany(targetEntity = User.class, mappedBy = "attentionQuestions", fetch = FetchType.LAZY)
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    /* 问题的评论 */
    @OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

}
