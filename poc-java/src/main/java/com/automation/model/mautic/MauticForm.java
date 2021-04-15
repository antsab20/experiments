package com.automation.model.mautic;


import lombok.Builder;
import lombok.Data;

import javax.json.bind.annotation.JsonbProperty;

@Data
@Builder
public class MauticForm {
    @JsonbProperty("aanhef1")
    private String title;
    @JsonbProperty("voornaam")
    private String firstName;
    @JsonbProperty("achternaam")
    private String lastName;
    @JsonbProperty("emailadres")
    private  String email;
    @JsonbProperty("phone_number")
    private String phoneNumber;
    @JsonbProperty("bedrijfsnaam")
    private String companyName;
    @JsonbProperty("function")
    private String function;
    @JsonbProperty("sector")
    private String sector;
    @JsonbProperty("country")
    private String country;
    private String contactId;
}
