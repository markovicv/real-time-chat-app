package com.example.ChatRealTimeService.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long lastUpdate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_chat",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @OneToMany(mappedBy = "chat",fetch = FetchType.LAZY)
    private List<Message> messageList;

    public Long getId() {
        return id;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }



    public List<Message> getMessageList() {
        return messageList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", lastUpdate=" + lastUpdate +
                ", users=" + users +
                ", messageList=" + messageList +
                '}';
    }
}
