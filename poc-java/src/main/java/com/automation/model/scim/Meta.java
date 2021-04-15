package com.automation.model.scim;

import lombok.Data;

import javax.json.bind.annotation.JsonbDateFormat;
import java.net.URL;
import java.util.Date;

@Data
public class Meta {
    private String resourceType;
    @JsonbDateFormat(value = "yyyy-MM-dd HH:mm:ss")
    private Date created;
    @JsonbDateFormat(value = "yyyy-MM-dd HH:mm:ss")
    private Date lastModified;
    private URL location;
}
