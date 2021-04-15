package com.automation.mapper;

import com.automation.model.mautic.MauticForm;
import lombok.NonNull;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MultivaluedMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@ApplicationScoped
public class RequestToMauticFormMapper {

    @NonNull
    public MauticForm map(@NonNull MultivaluedMap<String, String> input) {
        Map<String,String> parameters = new HashMap<>();
        Iterator<String> it = input.keySet().iterator();
        while(it.hasNext()){
            String theKey = (String)it.next();
            parameters.put(theKey, input.getFirst(theKey));
        }

        return MauticForm.builder()
                .title(input.getFirst("aanhef1"))
                .firstName(parameters.get("voornaam"))
                .lastName(parameters.get("achternaam"))
                .email(parameters.get("emailadres"))
                .phoneNumber(parameters.get("phone_number"))
                .companyName(parameters.get("bedrijfsnaam"))
                .function(parameters.get("function"))
                .sector(parameters.get("sector"))
                .country(parameters.get("country"))
                .contactId(parameters.get("mautic_contact[id]"))
                .build();
    }
}
