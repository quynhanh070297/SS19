package rikkei.thymeleafbaitap.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Employee")
public class Employee
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name ="name" )
    @NotEmpty
    private String name;
    @Column(name = "address" )
    @NotEmpty
    private String address;
    @Column(name = "dateOfBirth" )
    @NotNull
    @Past
    @DateTimeFormat (pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    @Column(name = "phone" )
    @NotEmpty
    private String phone;


}
