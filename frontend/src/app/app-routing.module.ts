import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddSurveyComponent } from './components/add-survey/add-survey.component';
import { ViewSurveyReportComponent } from './components/view-survey-report/view-survey-report.component';
import { ViewClaimComponent } from './components/view-claim/view-claim.component';
import { ReleaseFeeComponent } from './components/release-fee/release-fee.component';
import { UpdateClaimComponent } from './components/update-claim/update-claim.component';
import { AddClaimComponent } from './components/add-claim/add-claim.component';
import { IrdaComponent } from './components/irda/irda.component';
import { ShowReportsComponent } from './components/show-reports/show-reports.component';
import { HomeComponent } from './components/home/home.component';
import { UpdateComponent } from './components/update/update.component';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  { path: 'addSurvey', component: AddSurveyComponent },
  { path: 'viewSurvey', component: ViewSurveyReportComponent },
  { path: 'viewClaim', component: ViewClaimComponent },
  { path: 'addClaim', component: AddClaimComponent},
  { path: 'update/:id', component: UpdateClaimComponent },
  {path: 'release',component:ReleaseFeeComponent},
  {path:'irda',component:IrdaComponent },
  {path:'showreports',component:ShowReportsComponent},
  {path:'updateclaim',component:UpdateComponent},
  {path:'login',component:LoginComponent},

  {path:'',component:HomeComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
