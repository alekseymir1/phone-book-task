package com.beamtrail.phonebooking.web;

import com.beamtrail.phonebooking.dto.PhoneAvailability;
import com.beamtrail.phonebooking.entity.Phone;
import com.beamtrail.phonebooking.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/phone")
public class PhoneController {

    @Autowired
    private PhoneService phoneRepository;

    /**
     * Returns all phones from the storage
     * @return
     */
    @GetMapping
    public List<Phone> listAllPhones(){
        return phoneRepository.listAllPhones();
    }

    /**
     * Returns information about phone and it availability for booking
     * @return
     */
    @GetMapping("/availability")
    public List<PhoneAvailability> listAvailability(){
        return phoneRepository.listPhoneAvailability();
    }
}
