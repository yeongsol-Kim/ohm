package com.ohm.service;


import com.ohm.s3.AmazonS3ResourceStorage;
import com.ohm.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import com.ohm.config.AppConfig;
import com.ohm.dto.PostDto.PostDto;
import com.ohm.dto.responseDto.PostResponseDto;
import com.ohm.entity.Gym.Gym;
import com.ohm.entity.Post.Post;
import com.ohm.entity.Post.PostImg;
import com.ohm.repository.gym.GymRepository;
import com.ohm.repository.post.PostImgRepository;
import com.ohm.repository.post.PostRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {


    private final AmazonS3ResourceStorage amazonS3ResourceStorage;

    private final PostRepository postRepository;
    private final GymRepository gymRepository;
    private final PostImgRepository postImgRepository;
    private final AppConfig appConfig;

    @Transactional
    public void delete_imgs(List<Long> ids)  {

        for (Long id : ids) {
            PostImg postImg = postImgRepository.findById(id).get();
            amazonS3ResourceStorage.deleteObjectByKey(postImg.getFilePath());
            postImgRepository.delete(postImg);
        }
    }

    @Transactional
    public Long save_content(Long gymId, PostDto postDto)  {
        Optional<Gym> gym = gymRepository.findById(gymId);
        Post post = Post.builder()
                .createdBy(SecurityUtils.getCurrentUsername().get())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .gymId(gym.get().getId())
                .build();

        Post save = postRepository.save(post);
        return save.getId();
    }




    @Transactional
    public Long saveImg(Long postId, List<MultipartFile> files) throws Exception {
        Optional<Post> post = postRepository.findById(postId);
        if (files == null) {

        } else {

            for (MultipartFile multipartFile : files) {
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter dateTimeFormatter =
                        DateTimeFormatter.ofPattern("yyyyMMdd");
                String current_date = now.format(dateTimeFormatter);
                String uuid_string = UUID.randomUUID().toString();


                String ext = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
                //url,orignName

                // 파일 DTO 생성
                PostImg postImg = PostImg.builder()
                        .post(post.get())
                        .origFileName(multipartFile.getOriginalFilename())
                        .filePath(current_date + File.separator + uuid_string + ext)
                        .build();

                amazonS3ResourceStorage.upload(multipartFile, current_date, uuid_string + ext);
                postImgRepository.save(postImg);
            }

        }

        return post.get().getId();

    }



    public Slice<PostResponseDto> findall(Long gymid, int page) {
        PageRequest pageRequest = PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "createdTime"));
        Slice<Post> by_gymId = postRepository.findBy_gymId(gymid, pageRequest);

        Slice<PostResponseDto> postDtos = by_gymId.map(
                p -> appConfig.modelMapper().map(p,PostResponseDto.class));
        return postDtos;
    }

    //post id로 조회
    public PostResponseDto findById(Long id) {
        Optional<Post> byId = postRepository.findById(id);
        PostResponseDto postDto = appConfig.modelMapper().map(byId.get(), PostResponseDto.class);
        return postDto;
    }

    //변경감지 게시물 수정 (클라이언트에서 수정된 사항은 해당 객체에 업데이트해서 넣고 아닌 값은 원래 객체 값을 대입해서 넣어주자)
    @Transactional
    public Optional<Post> updatePost(PostDto postDto) {
        Optional<Post> byId = postRepository.findById(postDto.getId());
        byId.get().update(appConfig.modelMapper().map(postDto, Post.class));
        return byId;
    }

    @Transactional
    public void delete(Long postId) {
        Optional<Post> byId = postRepository.findById(postId);
        postRepository.delete(byId.get());
    }


}
