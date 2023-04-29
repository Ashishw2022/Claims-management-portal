import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse} from '@angular/common/http';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment.prod';




@Injectable({
  providedIn: 'root'
})
export class ClaimService {
 
  data:any=[];
  claimReport:any=[];
  paymentReport:any=[];

  constructor(private http: HttpClient, private router: Router) { }


  pendingClaim(month:any,year:any){

    return this.http
      .post(
        environment.IRDA_SERVICE+'claimStatus/pull/'+month+'/'+year,{})                 
      .pipe(
        catchError(this.handleError),
        tap((response) => {
          this.data = response;
        })
        );
  }
  pendingPayment(month:any,year:any){

    return this.http
      .post(
        environment.IRDA_SERVICE+'paymentStatus/pull/'+month+'/'+year,{})                 
      .pipe(
        catchError(this.handleError),
        tap((response) => {
          this.data = response;
        })
        );
  }
  showPendingClaim(month:any,year:any){

    return this.http
      .get(
        environment.IRDA_SERVICE+'claimStatus/report/'+month+'/'+year,{})                 
      .pipe(
        catchError(this.handleError),
        tap((response) => {
          this.claimReport = response;
        })
        );
  }
  showPendingPayment(month:any,year:any){

    return this.http
      .get(
        environment.IRDA_SERVICE+'paymentStatus/report/'+month+'/'+year,{})                 
      .pipe(
        catchError(this.handleError),
        tap((response) => {
          this.paymentReport = response;
        })
        );
  }

  handleError(errorResponse: HttpErrorResponse) {
    return throwError(errorResponse.error.message);
  }
}
