package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * Domain object represent one loan on Zonky market
 *
 * Note: Object does not contains all attributes from real JSON response,
 *       because application show only short enum values for loan.
 *
 * Created by Jiří Cága
 */
@JsonIgnoreProperties
public class Loan implements Serializable {

    private Long id;
    private String name;
    private String story;
    private BigDecimal amount;
    private String rating;

    private Date datePublished;
    private Date deadline;

    private String userId;
    private String nickName;

    // Constructor
    public Loan() {
    }



    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(id, loan.id) &&
                Objects.equals(name, loan.name) &&
                Objects.equals(story, loan.story) &&
                Objects.equals(amount, loan.amount) &&
                Objects.equals(rating, loan.rating) &&
                Objects.equals(datePublished, loan.datePublished) &&
                Objects.equals(deadline, loan.deadline) &&
                Objects.equals(userId, loan.userId) &&
                Objects.equals(nickName, loan.nickName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, story, amount, rating, datePublished, deadline, userId, nickName);
    }

    @Override
    public String toString() {
        return "Loan with id " + id + "\n{" +
                "\n\tname='" + name + '\'' +
                ", \n\tstory='" + story + '\'' +
                ", \n\tamount=" + amount +
                ", \n\trating='" + rating + '\'' +
                ", \n\tdatePublished=" + datePublished +
                ", \n\tdeadline=" + deadline +
                ", \n\tuserId='" + userId + '\'' +
                ", \n\tnickName='" + nickName + '\'' +
                "\n}\n";
    }
}
