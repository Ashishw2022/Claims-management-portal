import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Claim } from 'src/app/Claim';
import { SurveyorService } from 'src/app/services/surveyor.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-survey',
  templateUrl: './add-survey.component.html',
  styleUrls: ['./add-survey.component.css']
})
export class AddSurveyComponent implements OnInit {
  selectedClaimid = null;
  options=[{ claim_Id: 'Select a Claim Id' }];
  policyNo!:String;
  constructor(private service: SurveyorService, private router: Router,private http: HttpClient) { }

  data: any;

  Surveyorform = new FormGroup({
    claimId: new FormControl('', [Validators.required, Validators.min(10000000000)]),
    policyNo: new FormControl('', [Validators.required, Validators.min(1000000)]),
    policyClass: new FormControl('', [Validators.required]),
    partsCost: new FormControl('', [Validators.required, Validators.min(0)]),
    labourCharges: new FormControl('', [Validators.required, Validators.min(0)]),
    depreciationCost: new FormControl('', [Validators.required])
  });

  ngOnInit(): void {
    this.http.get<any[]>('http://localhost:8081/api/claims').subscribe((data) => {
      this.options = data;
      console.log(data);
    });
  }
  onSelectClaimId(event: Event) {
    const selectedClaimId = (event.target as HTMLSelectElement).value;
    console.log('Selected claim ID:', selectedClaimId);
    this.http.get<Claim>(`http://localhost:8081/api/claims/${selectedClaimId}`).subscribe((data) => {
     this.policyNo=data.policy_No;
      console.log( this.policyNo);
    });
    // You can perform additional logic here based on the selected claim ID
  }
  add() {
    this.data = this.Surveyorform.value;
    console.log(this.data);

    this.service.addSurvey(this.data).subscribe(
      data => {
        let m = data;
        Swal.fire({
          icon: 'success',
          title: m.claimId+`\nAmount Allocated`,
          showConfirmButton: true,
        });
         this.http.put<Claim>(`http://localhost:8081/api/claims/${data.claimId}/${data.totalAmount}/update`,"").subscribe((data) => {
          
           console.log( data);
         });

       
      },
      errorMessage => {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Please Check Your Details and try again',
        });
        console.log(errorMessage);
      }
    );
  }
}
