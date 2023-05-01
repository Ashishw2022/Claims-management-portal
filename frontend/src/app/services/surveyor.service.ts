import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Survey } from '../survey';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SurveyorService {

  private headers = new HttpHeaders({
    Authorization: `Bearer ${localStorage.getItem('token')}`
  });
  private url = "http://localhost:8080/";

  constructor(private http: HttpClient) { }

  addSurvey(survey:Survey) {
    console.log(this.headers);
    return this.http.post<Survey>(`${this.url}api/surveyors/new`,survey,{headers:this.headers})
  }
 
  getSurveyReport(claimId: number): Observable<Survey>{
    return this.http.get<Survey>(`${this.url}api/survey/${claimId}`,{headers:this.headers})
  }
  updateSurvey(claimId:number,survey:Survey): Observable<Survey>{
    return this.http.put<Survey>(`${this.url}api/survey/${claimId}`,survey,{headers:this.headers})
  }
}
