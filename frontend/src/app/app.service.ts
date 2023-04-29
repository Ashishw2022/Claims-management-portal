import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { Claim } from './Claim';
import { Fee } from './Fee';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  private url = "http://localhost:8081/";

  constructor(private http: HttpClient) { }

  addClaim(claim:Claim)
  {
    const newData={
      ...claim
    };
    return this.http.post<Claim>(`${this.url}api/claims/new`, newData)
  }
    
  getClaims():Observable<any[]>{
    return this.http.get<any[]>(`${this.url}api/claims`)
  } 


  updateClaim(id?: String,claim?:any):Observable<any>{
    const newData={
      ...claim
  };

    return this.http.put<any>(`${this.url}api/claims/${id}/update`, newData)
  }

  getClaimsById(id: String): Observable<Claim>{
       return this.http.get<Claim>(`${this.url}api/claims/${id}`)
  }

  getFeeDetailById(id:String): Observable<Fee>{
    return this.http.get<Fee>(`${this.url}api/surveyorfees/${id}`)
  }

  updateFeeDetails(id?: String,fee?:any):Observable<any>{
    const newData={
      ...fee
    };
    return this.http.put<any>(`${this.url}api/surveyorfees/${id}`,newData) 
  }
}
