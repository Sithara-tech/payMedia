@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email
    @Column(unique = true)
    private String email;

    @NotEmpty
    private String phone;

    @OneToOne
    private Department department;
}
