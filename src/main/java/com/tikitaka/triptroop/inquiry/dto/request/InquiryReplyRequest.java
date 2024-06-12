package com.tikitaka.triptroop.inquiry.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InquiryReplyRequest {

    private String reply;

    @JsonCreator
    public InquiryReplyRequest(@JsonProperty("reply") String reply) {
        this.reply = reply;
    }

}
