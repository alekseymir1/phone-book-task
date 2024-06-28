package com.beamtrail.phonebooking.repo;


import com.beamtrail.phonebooking.dto.PhoneAvailability;
import com.beamtrail.phonebooking.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    @Query("""
    select
        p.id as phoneId,
        p.model as phoneName,
        case when r.id is not null then r.bookedBy else null end as bookedBy,
        case when r.id is not null then false else true end as available,
        case when r.id is not null then r.bookedAt else null end as bookedAt
    from Phone p
    left join PhoneReservation r on p.id=r.phone.id
    """)
    public List<PhoneAvailability> listPhoneAvailability();
}
