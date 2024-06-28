package com.beamtrail.phonebooking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * A projection dto to represent phone availability
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface PhoneAvailability {
    Long getPhoneId();
    String getPhoneName();
    boolean isAvailable();
    String getBookedBy();

    Date getBookedAt();
}
