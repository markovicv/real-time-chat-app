import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AnalyticsComponent } from './analytics/analytics.component';
import { AuthGuard } from './auth.guard';
import { ChatComponent } from './chat/chat.component';
import { LoginComponent } from './login/login.component';
const routes: Routes = [

  {path:"login",component:LoginComponent},
  {path:"chatApp",component:ChatComponent},
  {path:"analytics",component:AnalyticsComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
