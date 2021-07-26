import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ChatMessage } from './model/chat-message';
import { Friend } from './model/friends';


declare var SockJS:any;
declare var Stomp:any;
@Injectable({
  providedIn: 'root'
})
export class MessageService {

  public stompClient;
  public friendsList:Array<Friend> = [];



  constructor(private httpClient:HttpClient) { }


  initWebSocket(){
    const websocket = new SockJS('http://localhost:9092/websocket')

    this.stompClient = Stomp.over(websocket);
    this.stompClient.connect({},this.onConnected,this.onError);


  }

  onConnected = ()=>{
    console.log("uspesno konektovan!");
    console.log(localStorage.getItem("currentId"));
    this.stompClient.subscribe("/user/"+String(localStorage.getItem("currentId"))+"/queue/messages",incomingMsg=>{
      
    })

  }
  onError = (err)=>{
    console.log(err);

  }

  sendMessage(){
    let msg = new ChatMessage();
    let currentId = Number(localStorage.getItem("currentId"));
    msg.chatId = -1;
    msg.data = "RADDDDIII";
    msg.senderId = currentId;
    msg.receiverId =3;
    
    this.stompClient.send("/app/chat",{},JSON.stringify(msg));
  }

  getAllFriendsList(){
    let currentUsernameId = Number(localStorage.getItem("currentId"));

    this.httpClient.get<any>("http://localhost:9092/friends/"+currentUsernameId).subscribe(data=>{
      this.friendsList = data;
    })
  }


}
