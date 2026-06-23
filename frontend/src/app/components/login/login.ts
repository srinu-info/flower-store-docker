import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';
import { Router } from '@angular/router';

import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login{

  loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {

    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  login(): void {

    if (this.loginForm.invalid) {
      alert('Please enter valid details');
      return;
    }

    this.authService.login(this.loginForm.value)
      .subscribe({

        next: (response: any) => {

  localStorage.setItem(
    'token',
    response.token
  );

  localStorage.setItem(
    'role',
    response.role
  );

  const email = this.loginForm.get('email')?.value;

localStorage.setItem(
  'name',
  email.split('@')[0]
);

  alert('Login Successful');

  window.location.href = '/';

},

        error: (error: any) => {

          console.error(error);

          alert('Invalid Credentials');
        }

      });
  }
}