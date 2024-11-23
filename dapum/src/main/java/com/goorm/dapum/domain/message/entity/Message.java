package com.goorm.dapum.domain.message.entity;


import com.goorm.dapum.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private Member sender;  // 발신자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private Member receiver;  // 수신자

    @Column(nullable = false)
    private String content;  // 메시지 내용

    @Column(nullable = false)
    private boolean isRead = false;  // 읽음 여부 (기본값 false)

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;  // 전송 시간

    // @PrePersist 메서드로 생성 시간 자동 설정
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}

