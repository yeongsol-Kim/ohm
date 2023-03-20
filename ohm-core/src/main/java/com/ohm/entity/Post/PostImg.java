package com.ohm.entity.Post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "post_img")
public class PostImg {


    @Id
    @GeneratedValue
    @Column(name = "post_img_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    //파일 원본명
    @Column(name = "origin_file_name")
    private String origFileName;

    //파일 저장 경로
    @Column(name = "file_path")
    private String filePath;


    @Builder
    public PostImg(Post post,String origFileName,String filePath){
        this.post = post;
        this.origFileName = origFileName;
        this.filePath = filePath;
    }


}
