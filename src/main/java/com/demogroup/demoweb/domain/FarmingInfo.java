package com.demogroup.demoweb.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FarmingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fid;

    private String topic;

    @Column(length = 255)
    private String summary;

    @Column(name = "title_1")
    private String title1;

    @Column(name="title_2")
    private String title2;

    @Column(name="content_1", columnDefinition = "TEXT")
    private String content1;

    @Column(name="content_2", columnDefinition = "TEXT")
    private String content2;

    @Column(name = "image_url_1")
    private String imageUrl1;

    @Column(name = "image_url_2")
    private String imageUrl2;

    @Column(name = "youtube_url")
    private String youtubeUrl;

    @ManyToOne
    @JoinColumn(name = "fcid")
    private FarmingInfoCategory category;

}
