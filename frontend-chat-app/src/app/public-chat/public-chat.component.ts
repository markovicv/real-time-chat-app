import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ChatRoomService } from '../chat-room.service';
import { PublicChatMessageDto } from '../model/public-chat-message';

@Component({
  selector: 'app-public-chat',
  templateUrl: './public-chat.component.html',
  styleUrls: ['./public-chat.component.css']
})
export class PublicChatComponent implements OnInit {

  messageToSend;

  constructor(private router:Router,public chatRoomService:ChatRoomService) { }

  ngOnInit(): void {

    this.chatRoomService.initWebSocket();

  }

  navigateToPrivateChat(){
    this.router.navigateByUrl("/chatApp");

  }
  navigateToPublicChat(){
    
  }
  send(){

    let publicChatMsg = new PublicChatMessageDto();
    publicChatMsg.senderId = this.getMyId();
    publicChatMsg.messageContent = this.messageToSend;
    this.chatRoomService.sendMessageToChatRoom(publicChatMsg);

    this.messageToSend = "";
  }

  getMyId(){
    return Number(localStorage.getItem("currentId"));
  }
}
