import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn:'root'
})
export class FeedbackService {

  private http = inject(HttpClient);
  private api = environment.apiUrl;

  private apiUrl =
    `${this.api}/feedback`;

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