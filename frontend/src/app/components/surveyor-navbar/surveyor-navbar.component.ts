import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-surveyor-navbar',
  templateUrl: './surveyor-navbar.component.html',
  styleUrls: ['./surveyor-navbar.component.css']
})
export class SurveyorNavbarComponent {
  constructor(private auth:AuthService,private router:Router) { }
logout(){
  this.auth.logout();
}
}
