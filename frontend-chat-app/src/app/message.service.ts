import { Injectable } from '@angular/core';
import { ChatMessage } from './model/chat-message';


declare var SockJS:any;
declare var Stomp:any;
@Injectable({
  providedIn: 'root'
})
export class MessageService {

  public stompClient;

  constructor() { }


  initWebSocket(){
    const websocket = new SockJS('http://localhost:9092/websocket')

    this.stompClient = Stomp.over(websocket);
    this.stompClient.connect({},this.onConnected,this.onError);


  }

  onConnected = ()=>{
    console.log("uspesno konektovan!");
    console.log(localStorage.getItem("currentId"));
    this.stompClient.subscribe("/user/"+String(localStorage.getItem("currentId"))+"/queue/messages",incomingMsg=>{
      console.log(incomingMsg.body);
    })

  }
  onError = (err)=>{
    console.log(err);

  }

  sendMessage(){
    let msg = new ChatMessage();
    let currentId = Number(localStorage.getItem("currentId"));
    msg.chatId = 1;
    msg.data = "RADDDDIII";
    msg.senderId = currentId;
    msg.receiverId =3;
    this.stompClient.send("/app/message/chat",{},JSON.stringify(msg));
  }


}
