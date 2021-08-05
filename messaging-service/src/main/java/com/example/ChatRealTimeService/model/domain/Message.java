package com.example.ChatRealTimeService.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long senderId;
    private Long receiverId;

    @Lob
    @Column(length = 512)
    private String data;
    @Enumerated(EnumType.STRING)
    private MessageStatus messageStatus;

    @ManyToOne
    @JoinColumn(name = "chat_id",nullable = false)
    @JsonIgnore
    private Chat chat;


}
