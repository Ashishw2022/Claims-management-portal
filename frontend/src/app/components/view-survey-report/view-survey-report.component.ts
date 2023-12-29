import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SurveyorService } from 'src/app/services/surveyor.service';
import { Survey } from 'src/app/survey';
import Swal from 'sweetalert2';
import { HttpClient } from '@angular/common/http';
import { Claim } from 'src/app/Claim';

@Component({
  selector: 'app-view-survey-report',
  templateUrl: './view-survey-report.component.html',
  styleUrls: ['./view-survey-report.component.css'],
})
export class ViewSurveyReportComponent {
  claimId!: number;
  surveyReport: any = null;
  survey!: Survey;
  errorMessage: string = '';
  data: any;
  response: any;
  Surveyorform = new FormGroup({
    claimId: new FormControl('', [Validators.required]),
    policyNo: new FormControl('', [Validators.required]),
    policyClass: new FormControl('', [Validators.required]),
    partsCost: new FormControl('', [Validators.required]),
    labourCharges: new FormControl('', [Validators.required]),
    depreciationCost: new FormControl('', [Validators.required]),
    totalAmount: new FormControl('', [Validators.required]),
  });
  constructor(
    private surveyReportService: SurveyorService,
    private router: Router,
    private http: HttpClient
  ) {}
  fetchSurveyReport() {
    this.data = this.claimId;
    console.log(this.data);

    this.surveyReportService.getSurveyReport(this.data).subscribe(
      (data) => {
        console.log(data);
        this.surveyReport = data;
      },
      (errorMessage) => {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Please Enter Valid Claim ID',
        });
        console.log(errorMessage);
      }
    );
  }
  update() {
    this.data = this.Surveyorform.value;
    console.log(this.data);
    this.surveyReportService.updateSurvey(this.claimId, this.data).subscribe(
      (data) => {
        this.surveyReport = data;
        Swal.fire({
          icon: 'success',
          title: 'Updated Sucessfully...',
          showConfirmButton: true,
        });
        this.http
          .put<Claim>(
            `http://localhost:8081/api/claims/${data.claimId}/${data.totalAmount}/update`,
            ''
          )
          .subscribe((response: any) => {
            console.log(response);
          });
      },
      (errorMessage) => {
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
