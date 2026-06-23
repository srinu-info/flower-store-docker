import {
  Component,
  OnInit
} from '@angular/core';

import {
  RouterModule
} from '@angular/router';

import {
  CommonModule
} from '@angular/common';

@Component({
  selector:'app-navbar',
  standalone:true,
  imports:[
    RouterModule,
    CommonModule
  ],
  templateUrl:'./navbar.html'
})
export class Navbar implements OnInit {

  userName='';

  role='';

  ngOnInit():void {

    this.userName =
      localStorage.getItem('name') || '';

    this.role =
      localStorage.getItem('role') || '';

  }

  logout() {

    localStorage.clear();

    window.location.href='/';

  }

}