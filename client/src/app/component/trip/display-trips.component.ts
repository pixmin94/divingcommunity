import { DivingService } from '../../service/diving.service';
import { Component, OnInit } from '@angular/core';
import { Trip } from '../../model/models';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-display-trips',
  templateUrl: './display-trips.component.html',
  styleUrls: ['./display-trips.component.css']
})
export class DisplayTripsComponent implements OnInit{
  trips!: Trip[]
  sub!: Subscription

  constructor(private service: DivingService) { }

  ngOnInit(): void {
    this.service.getTrips().subscribe(
      res => {
        console.log(res)
        this.trips = res
      }
    )
  }
}
