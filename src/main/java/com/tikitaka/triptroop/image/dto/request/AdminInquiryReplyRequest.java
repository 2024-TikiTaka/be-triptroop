package com.tikitaka.triptroop.image.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminInquiryReplyRequest {

    private String reply;

    @JsonCreator
    public AdminInquiryReplyRequest(@JsonProperty("reply") String reply) {
        this.reply = reply;
    }

}
