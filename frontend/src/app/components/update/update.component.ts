import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClaimsServiceService } from 'src/app/services/claims.service';
import Swal from 'sweetalert2';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {
  data:any=[];
  data2:any=[];
  isAccepted: boolean = false;
  isWithdrwan: boolean = false;
    constructor(private claimsService: ClaimsServiceService,
    private router: Router,
    private route: ActivatedRoute) { }

form = new FormGroup({
  policy_No: new FormControl('', [Validators.required, Validators.min(1000000)]),
  estimated_Loss: new FormControl('', [Validators.required]),
  date_Of_Accident: new FormControl('',[Validators.required])
})


  ngOnInit(): void {
    this.claimsService.allClaims().subscribe(
      (response: any)=>{
        console.log(response);
        this.data = (response);
        console.log(this.data);
      },
      (errorMessage: any) => {
        console.log(errorMessage);
      }
    )
  }
  acceptAmount(ClaimId:any){
    console.log(ClaimId);
        Swal.fire({
          title: 'Do you want Accept?',
          showDenyButton: true,
          showCancelButton: true,
          confirmButtonText: 'Accept',
          denyButtonText: `No`,
        }).then((result) => {
          if (result.isConfirmed) {
            this.claimsService.acceptClaim(ClaimId).subscribe(
              (response: any)=>{
                console.log();
                console.log(this.data2);
                this.isAccepted=  false;
                this.isWithdrwan=true;   
                this.router.navigate(['insured/updateinsured']);
            Swal.fire('Congratulations!', '', 'success')
          },(errorMessage: any) => {
            console.log(errorMessage);
          }) 
        }else if (result.isDenied) {
          
            Swal.fire('No Changes Were Made ', '', 'info')
          }
        })

  }



withdraw(ClaimId:any){
  console.log(ClaimId);
  console.log(ClaimId);
  Swal.fire({
    title: 'Do you want Accept?',
    showDenyButton: true,
    showCancelButton: true,
    confirmButtonText: 'Withdraw?',
    denyButtonText: `No`,
  }).then((result) => {
    /* Read more about isConfirmed, isDenied below */
    if (result.isConfirmed) {
      this.claimsService.withdrawClaim(ClaimId).subscribe(
        (response: any)=>{
          console.log();
          console.log(this.data2);
            this.isAccepted=  true;
            this.isWithdrwan=false; 
          this.router.navigate(['insured/updateinsured']);
      Swal.fire('Claim Withdrawn!', '', 'success')
    },(errorMessage: any) => {
      console.log(errorMessage);
    }) 
  }else if (result.isDenied) {
    
      Swal.fire('No Changes Were Made ', '', 'info')
    }
  })
  }

}
