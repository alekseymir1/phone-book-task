package com.beamtrail.phonebooking.repo;


import com.beamtrail.phonebooking.entity.PhoneReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<PhoneReservation, Long> {
    Optional<Object> findByPhoneId(Long phoneId);

    long deleteByPhoneId(Long phoneId);
}
