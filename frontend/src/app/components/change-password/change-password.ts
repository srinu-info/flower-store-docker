import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-change-password',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './change-password.html'
})
export class ChangePassword {

  oldPassword = '';
  newPassword = '';
  confirmPassword = '';

  changePassword() {

    if (this.newPassword !== this.confirmPassword) {

      alert('Passwords do not match');
      return;

    }

    alert('Change Password API will be called here');

  }

}