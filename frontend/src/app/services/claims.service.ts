import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';



import { Observable, catchError, tap, throwError } from 'rxjs';
import { environment } from 'src/environments/environment.prod';


// interface ClaimResponse 
//   {
//     insuranceCompanyApproval: boolean,
//     amountApprovedBySurveyor: number,
//     claimId: String,
//     dateOfAccident: Date,
//     claimStatus: boolean,
//     surveyorFees: number,
//     estimatedLoss: number,
//     withdrawClaim: boolean
// }
// interface UpdateClaim
// {
//   claimId: string,
//   dateOfAccident: Date,
//   claimStatus: boolean,
//   insuranceCompanyApproval: boolean,
//   amountApprovedBySurveyor: number,
//   withdrawClaim: boolean,
//   surveyorFees: number,
//   estimatedLoss: number
// }



//   export interface ClaimDetails{
//     claimStatus: boolean,
//     insuranceCompanyApproval: boolean,
//     amountApprovedBySurveyor: number,
//     surveyor:[surveyorId:number]
//     }
//   interface ClaimStatusResponse {
//     claimId: string;
//     claimStatus: string;
//     claimDescription: string;
//   }
  

//   interface Surveyor{
//     surveyorId: number,
//     firstName: string,
//     estimateLimit: number,
//     lastName: string
//   }
//   interface addClaim{
//     String:any
//   }
@Injectable({
  providedIn: 'root'
})
export class ClaimsServiceService {
  private url = "http://localhost:8081/";
  data:any=[];
    data2:any=[];
    Surveyor:any=[];
    singleClaim:any=[];
    claims:any=[];
    claimReport:any=[];
    paymentReport:any=[];
    // claimResponse: ClaimResponse | null = null;
    // claimStatusResponse: ClaimStatusResponse | null = null;
    // updateClaim:UpdateClaim|null=null;
    // surveyor:Surveyor |null=null;
    fee:any=[];
  
    constructor(private http: HttpClient, private router: Router) {
      interface singleClaim{
      
      }

  
  }
  addClaim(policyNo:any,estimatedLoss:any,dateOfAccident:any) {
    console.log("''''''''''''''''''''''''''''''''''''''")
    return this.http
      .post(
        environment.Insured_SERVICE_URL+'/addClaim',{policyNo,estimatedLoss,dateOfAccident},{responseType: 'text'})
                
      .pipe(
        catchError(this.handleError),
        tap((response) => {
          this.data = response;
        })
        );
       
} 
allClaims():Observable<any[]> {
  return this.http.get<any[]>(`${this.url}api/claims`)
}

withdrawClaim(claim_Id:any) {
  console.log("''''''''''''''''''''''''''''''''''''''")
  return this.http
    .put(
      environment.Insured_SERVICE_URL+'/update/'+claim_Id,{claim_Status:false,with_Draw_Claim:true},{responseType: 'text'})
              
    .pipe(
      catchError(this.handleError),
      tap((response) => {
        this.data = response;
      })
      );
}
acceptClaim(claim_Id:any) {
  console.log("''''''''''''''''''''''''''''''''''''''")
  return this.http
    .put(
      environment.Insured_SERVICE_URL+'/update/'+claim_Id,{claim_Status:false,with_Draw_Claim:false},{responseType: 'text'})
              
    .pipe(
      catchError(this.handleError),
      tap((response) => {
        this.data = response;
      })
      );
}
handleError(errorResponse: HttpErrorResponse) {
  return throwError(errorResponse.error.message);
}
}