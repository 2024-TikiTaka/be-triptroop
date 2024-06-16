package com.tikitaka.triptroop.admin.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AdminInquiryReplyRequest {

    private String reply;

    @JsonCreator
    private AdminInquiryReplyRequest(
            @JsonProperty("reply") String reply
    ) {
        this.reply = reply;
    }

    public static AdminInquiryReplyRequest of(String reply) {
        return new AdminInquiryReplyRequest(reply);
    }


}
