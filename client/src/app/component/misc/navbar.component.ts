
import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { DivingService } from 'src/app/service/diving.service';
import { selectIsLoggedIn } from 'src/app/store/user.selectors';



@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{
  // isLoggedIn: boolean = false
  isLoggedIn$!: Observable<boolean>

  constructor(
    private service: DivingService,
    private store: Store
  ) {
    this.service.initUserState()
  }

  ngOnInit(): void {

    this.isLoggedIn$ = this.store.select(selectIsLoggedIn)

    // this.service.isLoggedIn$.subscribe((logged) =>
    //   this.isLoggedIn = logged)

    // this.isLoggedIn = this.service.updateLoggedInStatus();

    // this.cookieSvc.get('username').subscribe(() => {
    //   this.updateLoggedInStatus()
    // })
  }

  logout() {
    this.service.logout()
    alert("Logged out!")
  }

}
