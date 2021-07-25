import { Injectable } from '@angular/core';
import { PublicChatMessageDto } from './model/public-chat-message';


declare var SockJS:any;
declare var Stomp:any;

@Injectable({
  providedIn: 'root'
})
export class ChatRoomService {

  public publicMessageList:PublicChatMessageDto[] = [];
  public stompClient;

  constructor() { }

  initWebSocket(){
    const webSocket = new SockJS('http://localhost:9092/websocket');
    this.stompClient = Stomp.over(webSocket);
    let that = this;

    this.stompClient.connect({},()=>{
      that.stompClient.subscribe("/chatRoom",message=>{

        console.log("ALLLOOO BRAT MOJ");
        const chatMsg:PublicChatMessageDto = JSON.parse(message.body);
        this.publicMessageList.push(chatMsg);
    


      });
    },(error)=>{
      console.log(error);
    });


  }

  onConnected = ()=>{

    console.log("uspesno konektovan n chat Room");
    this.stompClient.subscribe('/user/chatRoom',message=>{
      const chatMsg:PublicChatMessageDto = JSON.parse(message.body);
      console.log("A JELI OSTROGA TI");
      this.publicMessageList.push(chatMsg);
    });

  }

  onError = ()=>{

  }

  sendMessageToChatRoom(message:PublicChatMessageDto){
    this.stompClient.send("/app/publicChat",{},JSON.stringify(message));
  }
}
