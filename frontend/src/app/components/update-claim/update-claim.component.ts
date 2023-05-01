import { Component,OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Claim } from 'src/app/Claim';
import { Surveyor } from 'src/app/Surveyor';
import { AppService } from 'src/app/app.service';
import { HttpClient } from '@angular/common/http';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-update-claim',
  templateUrl: './update-claim.component.html',
  styleUrls: ['./update-claim.component.css']
})
export class UpdateClaimComponent implements OnInit{

  claim?:Claim
  data:any
  surveyor!: any[];

  constructor(private service: AppService, private route: ActivatedRoute, private router : Router,private http: HttpClient) { }

  claim_Status = 'open';
  get isClosed(): boolean {
    return this.claim_Status === 'closed';
  }
  
  form = new FormGroup({
    claim_Status: new FormControl('', [Validators.required, Validators.pattern('^[a-z A-Z]+$')]),
    surveyor_Id: new FormControl(),
    insurance_Company_Approval:new FormControl()
  
  })

  ngOnInit(): void {
  
    let Claim_Id = this.route.snapshot.params['id'];
    this.service.getClaimsById(Claim_Id).subscribe(data => {
      this.claim = data
      console.log(this.claim)
        this.form.patchValue(this.claim); 
    });
    this.http.get<any[]>('http://localhost:8081/api/surveyors').subscribe((data) => {
      this.surveyor = data;
      console.log(data);
    });
    
    
  }

  submit(){
    this.data = this.form.value
    let Claim_Id = this.route.snapshot.params['id'];
    this.service.getClaimsById(Claim_Id).subscribe(value => {
      this.claim = value
      console.log(this.claim)
    if(this.form.value.insurance_Company_Approval && this.claim.amount_ApprovedBy_Surveyor!=0 )
    {
      this.data.with_Draw_Claim=true;
      this.data.insurance_Company_Approval=true;
      Swal.fire({
        icon: 'success',
        title: `hurray`,
        text: 'Claim details Updated successfully',
        showConfirmButton: true,
      });
    }
    else 
    {
      this.data.with_Draw_Claim=false;
      this.data.insurance_Company_Approval=false;
      if(this.claim.amount_ApprovedBy_Surveyor==0)
      {
         Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Amount is not updated by the surveyor ',
        });
      } 
      else{
        Swal.fire({
          icon: 'success',
          title: `hurray`,
          text: 'Claim details Updated successfully',
          showConfirmButton: true,
        });

      } 
    }
    console.log(this.data);
    this.service.updateClaim(this.claim?.claim_Id, this.data).subscribe(data => {
       //console.log(data)
      console.log(data)
      setTimeout(()=>{
        window.location.reload();
      }, 100);
    })

  })
    this.router.navigate(['/']);
    // alert('details Updated successfully')
  }

  
   
}
