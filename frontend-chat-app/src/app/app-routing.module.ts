import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AnalyticsComponent } from './analytics/analytics.component';
import { AuthGuard } from './auth.guard';
import { ChatComponent } from './chat/chat.component';
import { LoginComponent } from './login/login.component';
const routes: Routes = [

  {path:"login",component:LoginComponent},
  {path:"chatApp",component:ChatComponent,canActivate:[AuthGuard]},
  {path:"analytics",component:AnalyticsComponent,canActivate:[AuthGuard]}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
