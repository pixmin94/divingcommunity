import { Trip } from './../model/models';
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, firstValueFrom } from 'rxjs';

@Injectable()
export class DivingService {
  constructor(private http: HttpClient) {  }

  createTrip(trip: Trip): Promise<any> {
    return firstValueFrom(
      this.http.post<Trip>('/api/createtrip', trip)
    )//.then(result => console.log(result))
  }

  getTrips(): Observable<Trip[]> {
    return this.http.get<any>('/api/gettrips')
  }
}
