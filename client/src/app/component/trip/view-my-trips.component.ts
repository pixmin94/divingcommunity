import { CookieService } from 'ngx-cookie-service';
import { DivingService } from '../../service/diving.service';
import { Component, OnInit } from '@angular/core';
import { Trip } from '../../model/models';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-view-my-trips',
  templateUrl: './view-my-trips.component.html',
  styleUrls: ['./view-my-trips.component.css']
})
export class ViewMyTripsComponent {
  trips!: Trip[]
  sub!: Subscription

  constructor(
    private service: DivingService,
    private cookieSvc: CookieService
  ) { }

  leaveTrip(tripId: number) {
    this.service.leaveTrip(tripId.toString(), this.cookieSvc.get('username'))
      .then(res => {
        alert(res)
        //
      })
  }

  ngOnInit(): void {
    this.service.getMyTrips().subscribe(
      res => {
        console.log(res)
        this.trips = res
      }
    )
  }
}
