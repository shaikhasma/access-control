package com.gotech.accesscontrol.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class Response {    // response dto

    @JsonProperty("status-code")
    private String statusCode;

    @JsonProperty("status-message")
    private String statusMessage;
}
