package com.ohm.entity.Gym;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "gym_img")
public class GymImg {

    @Id
    @GeneratedValue
    @Column(name = "gym_img_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gym;

    //파일 원본명
    @Column(name = "origin_file_name")
    private String originFileName;

    //파일 저장 경로
    @Column(name = "file_path")
    private String filePath;

    @Builder
    public GymImg(Gym gym, String origFileName, String filePath) {
        this.originFileName = origFileName;
        this.gym = gym;
        this.filePath = filePath;
    }


}
