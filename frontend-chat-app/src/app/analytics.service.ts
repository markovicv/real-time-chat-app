import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MessageAmountSentToFriend } from './model/messages-sent';

@Injectable({
  providedIn: 'root'
})
export class AnalyticsService {

  constructor(private httpClient:HttpClient) {

   }


   getNumberOfMessagesSentToFriend():Observable<any>{
    let currentUsernameId = Number(localStorage.getItem("currentId"));

     return this.httpClient.get<any>("http://localhost:9093/analytics/send/amount/"+currentUsernameId);
   }


   getNumberOfMessagesReceivedFromFriends():Observable<any>{
    let currentUsernameId = Number(localStorage.getItem("currentId"));

     return this.httpClient.get<any>("http://localhost:9093/analytics/received/amount/"+currentUsernameId);
   }

}
