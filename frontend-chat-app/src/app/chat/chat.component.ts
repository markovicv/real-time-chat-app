import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  constructor(public messageService:MessageService,private router:Router) { }

  ngOnInit(): void {
    // this.messageService.initWebSocket();
    this.messageService.getAllFriendsList();
  }

  send(){
    this.messageService.sendMessage();
  }



  navigateToPrivateChat(){
    this.router.navigateByUrl("/chatApp");

  }
  navigateToPublicChat(){
    this.router.navigateByUrl("/publicChat");
  }

}
