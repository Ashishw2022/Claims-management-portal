import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username:string="";
  password:string="";
  invalid:boolean=false;
  
 
  constructor(private auth:AuthService,private router:Router) { }

  ngOnInit(): void {
  }

  login(formdata:any):void
  {
      console.log(formdata.user, formdata.password)
      this.auth.login(formdata.user,formdata.password);
      
    
  }

}