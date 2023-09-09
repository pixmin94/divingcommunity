import { DivingService } from '../../service/diving.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Trip } from '../../model/models';

@Component({
  selector: 'app-create-trip',
  templateUrl: './create-trip.component.html',
  styleUrls: ['./create-trip.component.css']
})
export class CreateTripComponent implements OnInit{
  form!: FormGroup
  tripID!: number

  constructor(private fb: FormBuilder,
      private service: DivingService) { }

  ngOnInit(): void {
    this.form = this.createForm()
  }

  submitTrip() {
    const trip: Trip = this.form.value
    // console.log(trip)
    this.service.createTrip(trip)
      .then(result => alert("Trip ID created: "+result))
      .catch(error => alert("Error: "+error))
  }

  createForm() {
    return this.fb.group({
      title: this.fb.control<string>(''),
      location: this.fb.control<string>(''),
      startDate: this.fb.control<Date>(new Date(0), [Validators.required]),
      endDate: this.fb.control<Date>(new Date(0), [Validators.required])
    })
  }
}