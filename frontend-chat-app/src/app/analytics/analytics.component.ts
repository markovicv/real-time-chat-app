import { Component, OnInit } from '@angular/core';
import { AnalyticsService } from '../analytics.service';
import { MessageAmountSentToFriend } from '../model/messages-sent';
import{Chart,registerables} from 'node_modules/chart.js';
import { element } from 'protractor';
import { MessageAmountReceivedFromriends } from '../model/messages-received';
import { timer } from 'rxjs';
import { Router } from '@angular/router';

Chart.register(...registerables);


@Component({
  selector: 'app-analytics',
  templateUrl: './analytics.component.html',
  styleUrls: ['./analytics.component.css']
})
export class AnalyticsComponent implements OnInit {

  numberOfMessagesSent:Array<MessageAmountSentToFriend> =[];
  
  numberOfMessagesSendPerFriend:Array<any>=[];
  friendsNameList:Array<any>=[];

  numberOfMessagesReceived:Array<MessageAmountReceivedFromriends> = [];

  numberOfMessagesReceivedFromFriends:Array<any> = [];
  friendsNamesReceived:Array<any> = [];
  
  messagesSentChart;
  messagesReceivedChart;


  constructor(private analyticsService:AnalyticsService,private router:Router) { }

  ngOnInit(): void {
   

    this.getNumberOfMessagesSentToFriend();
    this.getNumberOfMessagesReceivedFromFriends();
  
  }



  generateMessagesSentPerFriendChart(){
    this.messagesSentChart = new Chart("messagesSentToFriendsId", {
      type: 'bar',
      data: {
          labels: this.friendsNameList,
          datasets: [{
              label: 'number of messages sent to friends',

              data: this.numberOfMessagesSendPerFriend,
              backgroundColor: [
                  'rgba(54, 162, 235, 0.2)',
                 
              ],
              borderColor: [
                  'rgba(54, 162, 235, 1)',
                 
              ],
              borderWidth: 1
          }]
      },
      options: {
          scales: {
              y: {
                  beginAtZero: true
              }
          }
      }
  });
  }

  generateMessagesSentPerFriendPercentageChart(){
    this.messagesSentChart = new Chart("messagesSentToFriendsPercentChartId", {
        type: 'bar',
        data: {
            labels: this.friendsNameList,
            datasets: [{
                label: 'number of messages sent to friends',
  
                data: this.numberOfMessagesSendPerFriend,
                backgroundColor: [
                    'rgba(54, 162, 235, 0.2)',
                   
                ],
                borderColor: [
                    'rgba(54, 162, 235, 1)',
                   
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
  }

  generateMessagesReceivedPerFriendChart(){
      this.messagesReceivedChart = new Chart("messagesReceivedFromFriendsId", {
      type: 'bar',
      data: {
          labels: this.friendsNamesReceived,
          datasets: [{
              label: 'number of messages received from friends',
              data: this.numberOfMessagesReceivedFromFriends,
              backgroundColor: [
                  'rgba(153, 102, 255, 0.2)',
                 
              ],
              borderColor: [
                  'rgba(153, 102, 255, 1)',
                 
              ],
              borderWidth: 1
          }]
      },
      options: {
          scales: {
              y: {
                  beginAtZero: true
              }
          }
      }
  });
  }


  getNumberOfMessagesReceivedFromFriends(){

    this.analyticsService.getNumberOfMessagesReceivedFromFriends()
    .subscribe(data=>{
      
      // promeniti da bude kao u donjoj metodi
      this.numberOfMessagesReceived = data;

      this.numberOfMessagesReceived.forEach(element=>{
          this.numberOfMessagesReceivedFromFriends.push(element.numberOfMessagesReceivedToFriend);
          this.friendsNamesReceived.push(element.friendUsername);
      });

      this.generateMessagesReceivedPerFriendChart();
    })

  }

  getNumberOfMessagesSentToFriend(){
    this.analyticsService.getNumberOfMessagesSentToFriend()

    .subscribe(data=>{
        this.numberOfMessagesSent = data;
        this.numberOfMessagesSent.forEach(msgPerFriend=>{
          

          this.numberOfMessagesSendPerFriend.push(msgPerFriend.numberOfMessagesSentToFriend);
          this.friendsNameList.push(msgPerFriend.friendUsername);
        });
        

        this.generateMessagesSentPerFriendChart();

        
    })
    
  }

  navigateToPrivateChat(){
    this.router.navigateByUrl("/chatApp");

  }
  navigateToAnalytics(){
    this.router.navigateByUrl("/analytics");
  }


}
