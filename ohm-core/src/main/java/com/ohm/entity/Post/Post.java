package com.ohm.entity.Post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.ohm.entity.Gym.Gym;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "post")
public class Post {


    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @CreatedDate
    private LocalDateTime createdTime;

    @LastModifiedBy
    private LocalDateTime lastModifiedTime;

    @CreatedBy
    @Column(name = "create_by", updatable = false)
    private String createdBy;

    //글 이미지
    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<PostImg> imgs;

    @Column(name = "gym_id")
    private Long gymId;


    @Builder
    public Post(String title, String content, Long gymId, LocalDateTime createdTime,String createdBy) {
        this.title = title;
        this.createdTime = LocalDateTime.now();
        this.createdBy = createdBy;
        this.content = content;
        this.gymId = gymId;
    }


    //게시글 변경 메세드
    public void update(Post post) {
        this.id = post.getId();
        this.lastModifiedTime = LocalDateTime.now();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

}
