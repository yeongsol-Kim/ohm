package com.ohm.dto.PostDto;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostDto {

    private Long id;


    private String title;

    private String content;

    private List<PostImgDto> imgs;

    private Long gymId;

    public PostDto(String title,String content){
        this.title = title;
        this.content = content;
    }

    public PostDto(Long id,String title,String content){
        this.id =id;
        this.title = title;
        this.content = content;
    }


    //Post 저장 생성자
    public PostDto(String title,String content,Long gymId){
        this.id = id;
        this.title = title;
        this.content = content;
        this.gymId = gymId;
    }


}
