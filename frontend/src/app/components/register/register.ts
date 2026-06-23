import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './register.html'
})
export class Register{

  registerForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService
  ) {

    this.registerForm = this.fb.group({
      title: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      city: ['', Validators.required],
      country: ['', Validators.required],
      phone: ['', Validators.required]
    });

  }

  register() {

    if (this.registerForm.invalid) {
      return;
    }

    this.authService.register(this.registerForm.value)
  .subscribe({
   next: (res) => {

  console.log('SUCCESS', res);

  alert('Registration Successful');

  this.registerForm.reset();

  window.location.href = '/login';

},
    error: (err) => {
      console.log('FULL ERROR', err);
      alert('Registration Failed');
    }
  });
  }
}