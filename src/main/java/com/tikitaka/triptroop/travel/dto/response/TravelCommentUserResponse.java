package com.tikitaka.triptroop.travel.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TravelCommentUserResponse {


    private String title;

    private String content;


    private Long CommentId;

    private String CommentContent;

    private Long UserId;

    private String email;

    private String password;

    private String name;
    private String imageName;
    private String imagePath;
    

    public static TravelCommentUserResponse of(String title, String content, Long commentId, String commentContent, Long userId, String email, String password, String name, String imageName, String imagePath) {
        return new TravelCommentUserResponse(
                title,
                content,
                commentId,
                commentContent,
                userId,
                email,
                password,
                name,
                imageName,
                imagePath
        );

    }
}
