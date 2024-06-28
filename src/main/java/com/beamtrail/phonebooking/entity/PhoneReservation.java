package com.beamtrail.phonebooking.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"phone_id"}))
public class PhoneReservation {
    @Id
    @GeneratedValue
    private Long id;

    private String bookedBy;

    private Date bookedAt;

    @OneToOne
    @JoinColumn(name = "phone_id", referencedColumnName = "id")
    private Phone phone;
}
