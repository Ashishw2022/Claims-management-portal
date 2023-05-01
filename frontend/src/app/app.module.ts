import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddSurveyComponent } from './components/add-survey/add-survey.component';
import { ViewSurveyReportComponent } from './components/view-survey-report/view-survey-report.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AddClaimComponent } from './components/add-claim/add-claim.component';
import { ViewClaimComponent } from './components/view-claim/view-claim.component';
import { ReleaseFeeComponent } from './components/release-fee/release-fee.component';
import { UpdateClaimComponent } from './components/update-claim/update-claim.component';
import { IrdaComponent } from './components/irda/irda.component';
import { ShowReportsComponent } from './components/show-reports/show-reports.component';
import { HomeComponent } from './components/home/home.component';
import { UpdateComponent } from './components/update/update.component';
import { LoginComponent } from './components/login/login.component';
import { SurveyorNavbarComponent } from './components/surveyor-navbar/surveyor-navbar.component';

@NgModule({
  declarations: [
    AppComponent,
    AddSurveyComponent,
    ViewSurveyReportComponent,
    NavbarComponent,
    AddClaimComponent,
    ViewClaimComponent,
    ReleaseFeeComponent,
    UpdateClaimComponent,
    IrdaComponent,
    ShowReportsComponent,
    HomeComponent,
    UpdateComponent,
    LoginComponent,
    SurveyorNavbarComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,

    HttpClientModule,
    FormsModule,
    ReactiveFormsModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
