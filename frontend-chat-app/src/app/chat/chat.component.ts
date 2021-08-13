import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from '../message.service';
import { Friend } from '../model/friends';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  messageToSend;


  constructor(public messageService:MessageService,private router:Router) { }

  ngOnInit(): void {
    this.messageService.initWebSocket();
    this.messageService.getAllFriendsList();
  }

  send(){
    this.messageService.sendMessage(this.messageToSend);
    this.messageToSend="";
  }


  openChat(friend:Friend){

    this.messageService.openChat(friend);
  }

  navigateToPrivateChat(){
    this.router.navigateByUrl("/chatApp");

  }
  navigateToAnalytics(){
    this.router.navigateByUrl("/analytics");
  }

  firstClicked(){
    return this.messageService.isClicked;
  }
  getMyId(){
    return Number(localStorage.getItem("currentId"));
  }
}
