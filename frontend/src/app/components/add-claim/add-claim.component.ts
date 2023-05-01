import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AppService } from 'src/app/app.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-claim',
  templateUrl: './add-claim.component.html',
  styleUrls: ['./add-claim.component.css']
})
export class AddClaimComponent implements OnInit{

data:any

form = new FormGroup({
  policy_No: new FormControl('', [Validators.required, Validators.min(1000000)]),
  estimated_Loss: new FormControl('', [Validators.required]),
  date_Of_Accident: new FormControl('',[Validators.required])
})

constructor(private service: AppService, private router: Router,private http: HttpClient) { }

  ngOnInit(): void {
    this.data=this.form.value;
    console.log(this.data);
    
  }

  submit(){
    this.data=this.form.value;
    console.log(this.data);
    this.service.addClaim(this.data).subscribe(data =>{
       console.log(data)
       
       setTimeout(()=>{
        window.location.reload();
      }, 100);
    })
    this.router.navigate(['/viewClaim']);
    Swal.fire({
      icon: 'success',
      title: `hurray`,
      text: 'Claim added successfully',
      showConfirmButton: true,
    });
    
  }
  
}













