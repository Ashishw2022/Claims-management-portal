import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Survey } from '../survey';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SurveyorService {


  private url = "http://localhost:8080/";

  constructor(private http: HttpClient) { }

  addSurvey(survey:Survey) {
    return this.http.post<Survey>(`${this.url}api/surveyors/new`,survey)
  }
 
  getSurveyReport(claimId: number): Observable<Survey>{
    return this.http.get<Survey>(`${this.url}api/survey/${claimId}`)
  }
  updateSurvey(claimId:number,survey:Survey): Observable<Survey>{
    return this.http.put<Survey>(`${this.url}api/survey/${claimId}`,survey)
  }
}
