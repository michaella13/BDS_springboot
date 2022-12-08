package com.bloodApp.BDS.entity;
import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "emp")
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String blood_type;

    @Column(nullable = false)
    private Number num_of_packets;

    public Donor(String firstName, String lastName, String email,String blood_type, Number num_of_packets) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.blood_type = blood_type;
        this.num_of_packets= num_of_packets;
    }
}
