package com.gotech.accesscontrol.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class Response {

    @JsonProperty("status-code")
    private String statusCode;

    @JsonProperty("status-message")
    private String statusMessage;
}
