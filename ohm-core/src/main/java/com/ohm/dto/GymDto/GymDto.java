package com.ohm.dto.GymDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.ohm.dto.InputDto.InputDto;
import com.ohm.dto.ManagerDto.ManagerDto;
import com.ohm.entity.Post.Post;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GymDto {

    private Long id;
    @NotNull
    @Size(min = 1,max = 50)
    private String name;

    @NotNull
    @Size(min = 1,max = 50)
    private String address;

    //헬스장 총 인원
    private int count;

    private String area;


    @JsonIgnore
    private List<GymImgDto> imgs;

    private String introduce;

    private String onelineIntroduce;

    private int code;

    private Long currentCount;


    private int trainerCount;

    @JsonIgnore
    private final ArrayList<ManagerDto> manager = new ArrayList<>();

    @JsonIgnore
    private GymTimeDto gymTime;


    @JsonIgnore
    private GymPriceDto gymPrice;

    @JsonIgnore
    private List<InputDto> inputs;


    @JsonIgnore
    private final List<Post> posts = new ArrayList<Post>();

    //테스트용 생성자
    public GymDto(String name,int count, Long currentCount){
        this.name = name;
        this.count = count;
        this.currentCount = currentCount;
    }

    //GymDto save entity 생성자ㅣ
    public GymDto(String name,String address,int count,int code){
        this.name = name;
        this.address = address;
        this.count = count;
        this.code = code;
        //this.manager = managerDto.getGymDto().getManager();
    }

    public Long increase_count(){
        this.currentCount = this.currentCount + 1;
        return currentCount;
    }


    public Long decrease_count(){
        this.currentCount = this.currentCount - 1;
        return currentCount;
    }




}
