package com.beamtrail.phonebooking.service;

import com.beamtrail.phonebooking.dto.PhoneAvailability;
import com.beamtrail.phonebooking.entity.Phone;
import com.beamtrail.phonebooking.repo.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    /**
     * Lists all phones
     * @return
     */
    public List<Phone> listAllPhones() {
        return phoneRepository.findAll();
    }

    /**
     * Lists phones with information if it is available for booking
     * @return
     */
    public List<PhoneAvailability> listPhoneAvailability() {
        return phoneRepository.listPhoneAvailability();
    }
}
