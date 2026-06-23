import {
  Component,
  OnInit,
  inject,
  ChangeDetectorRef
} from '@angular/core';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { FeedbackService }
from '../../services/feedback';

@Component({
  selector: 'app-feedback',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './feedback.html'
})
export class Feedback implements OnInit {

  private feedbackService =
    inject(FeedbackService);

  private cdr =
    inject(ChangeDetectorRef);

  rating = 5;

  message = '';

  reviews:any[] = [];

  successMessage='';

  ngOnInit(): void {

    this.loadReviews();

  }

  submitFeedback(){

    const payload = {

      rating:this.rating,

      message:this.message

    };

    this.feedbackService
      .submitFeedback(payload)
      .subscribe({

        next:(response)=>{

          console.log(
            'Feedback Submitted',
            response
          );

          this.successMessage =
            'Review Submitted Successfully';

          this.message='';

          this.rating=5;

          this.loadReviews();

          setTimeout(()=>{

            this.successMessage='';

          },3000);

        },

        error:(err)=>{

          console.error(err);

          alert(
            'Failed To Submit Review'
          );

        }

      });

  }

  loadReviews(){

    this.feedbackService
      .getReviews()
      .subscribe({

        next:(data:any)=>{

          console.log(
            'Reviews',
            data
          );

          this.reviews =
            [...data];

          this.cdr.detectChanges();

        },

        error:(err)=>{

          console.error(err);

        }

      });

  }

}