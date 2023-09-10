import { DivingService } from './../service/diving.service';
import { Subscription } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{
  isLoggedIn: boolean = false

  constructor(private service: DivingService) { }

  ngOnInit(): void {
    this.service.isLoggedIn$.subscribe((logged) =>
      this.isLoggedIn = logged)
    // this.isLoggedIn = this.service.updateLoggedInStatus();

    // this.cookieSvc.get('username').subscribe(() => {
    //   this.updateLoggedInStatus()
    // })
  }



}
