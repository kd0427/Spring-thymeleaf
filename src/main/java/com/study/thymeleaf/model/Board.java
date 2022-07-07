package com.study.thymeleaf.model;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@RequiredArgsConstructor // 테이블과 연결할 클래스이기 때문에 기본 생성자 생성
@Data
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @Size(min=1, max=30, message = "제목은 2자이상 30자 이하 입니다.")
    private String title;

    @Size(min=1, message = "내용을 입력하세요.")
    private String content;

}
