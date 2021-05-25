import { Component, OnInit } from '@angular/core';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  constructor(public messageService:MessageService) { }

  ngOnInit(): void {
    // this.messageService.initWebSocket();
  }

  send(){
    this.messageService.sendMessage();
  }

}
