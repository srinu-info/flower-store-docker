import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn:'root'
})
export class FeedbackService {

  private http = inject(HttpClient);

  private apiUrl =
    'http://localhost:8080/feedback';

  submitFeedback(payload:any){

    return this.http.post(
      this.apiUrl,
      payload,
      {
        responseType:'text'
      }
    );

  }

  getReviews(){

    return this.http.get<any[]>(
      this.apiUrl
    );

  }

}