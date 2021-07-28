import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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
  public currentChatMessages:Array<ChatMessage> =[];
  public currentChatRoomId;
  public currentFriend:Friend;
  public isClicked:boolean = false;

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
      const chatMessage:ChatMessage = JSON.parse(incomingMsg.body);


      if(this.currentFriend!=null && chatMessage.senderId == this.currentFriend.friendId){
        this.currentChatMessages.push(chatMessage);
      }
      else{
        this.newUnreadMessages(chatMessage.senderId);
      }
    })

  }
  onError = (err)=>{
    console.log(err);

  }

  sendMessage(messageToSend:String  ){
    let msg = new ChatMessage();

    msg.chatId = this.currentChatRoomId;
    msg.data = messageToSend;
    msg.senderId = Number(localStorage.getItem("currentId"));
    msg.receiverId =this.currentFriend.friendId;


    this.httpClient.post<ChatMessage>("http://localhost:9092/chat",msg).subscribe(data=>{

      this.currentChatMessages.push(data);
      this.stompClient.send("/app/chat",{},JSON.stringify(data));



    });    
  }

  getAllFriendsList(){
    let currentUsernameId = Number(localStorage.getItem("currentId"));

    this.httpClient.get<any>("http://localhost:9092/friends/"+currentUsernameId).subscribe(data=>{

      

      this.friendsList = data;
    })
  }

  openChat(friend:Friend){
    this.currentFriend = friend;
    this.getAllMessagesBetweenUsers(Number(localStorage.getItem("currentId")),friend.friendId).subscribe(data=>{

        this.currentChatMessages = data.messageDtoList;

        this.isClicked=true;
        this.currentChatRoomId = data.chatId
        this.markMessagesAsDelivered(friend.friendId);        

  

    })

  }


  getAllMessagesBetweenUsers(senderId:number,receiverId:number):Observable<any>{
    return this.httpClient.get<any>("http://localhost:9092/messages/"+receiverId+"/"+senderId);
  }


  markMessagesAsDelivered(senderId:number){
    for(let friend of this.friendsList){
      if(friend.friendId == senderId){
        friend.numberOfUnreadMessages=0;
      }
    }
  }
  newUnreadMessages(senderId:number){
    for(let friend of this.friendsList){
      if(friend.friendId ==senderId){
        friend.numberOfUnreadMessages+=1;
      }
    }
  }
  getFriend(){
    for(let friend of this.friendsList){
      if (friend.friendId == this.currentFriend.friendId){
        return friend;
      }
    }
  }

}
