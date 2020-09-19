package TeamTask.models;

import TeamTask.models.User;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

@Entity
@Table(name = "token")
public class Token {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="token")
    private String token;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_token", joinColumns = @JoinColumn(name = "id_token"), inverseJoinColumns = @JoinColumn(name = "id_user"))
    private User user;


//    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

    @Column(name="created_date")
    private Date createdDate;

    @Column(name="expiry_date")
    private Date expiryDate;

    public Token() {
        super();
    }



    public Token(final String token) {
        super();
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public Token(final String token, final User user) {
        super();
        Calendar calendar = Calendar.getInstance();

        this.token = token;
        this.user = user;
        this.createdDate = new Date(calendar.getTime().getTime());
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public static int getEXPIRATION() {
        return EXPIRATION;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        // calendar.add(Calendar.MINUTE, expiryTimeInMinutes);
        // calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(calendar.getTime().getTime());
    }

}