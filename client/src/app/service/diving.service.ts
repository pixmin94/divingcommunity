import { Account, Trip, User } from './../model/models';
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { CookieService } from 'ngx-cookie-service';
import { BehaviorSubject, Observable, firstValueFrom } from 'rxjs';

@Injectable()
export class DivingService {
  constructor(private http: HttpClient, private cookieSvc: CookieService) {  }

  createTrip(trip: Trip): Promise<any> {
    return firstValueFrom(
      this.http.post<Trip>('/api/createtrip', trip)
    )//.then(result => console.log(result))
  }

  getTrips(): Observable<Trip[]> {
    return this.http.get<any>('/api/gettrips')
  }

  createAccount(account: Account): Observable<any> {
    return this.http.post('/api/createaccount', account, {responseType: 'text'})

  }

  login(user: User): Observable<any> {
    return this.http.post('/api/login', user, {responseType: 'text'})
  }

  private isLoggedInSubj = new BehaviorSubject<boolean>(false)
  isLoggedIn$ = this.isLoggedInSubj.asObservable()
  setLoggedIn(isLoggedIn: boolean) {
    this.isLoggedInSubj.next(isLoggedIn)
  }

  // updateLoggedInStatus() {
  //   const token = this.cookieSvc.get('username')
  //   this.isLoggedIn = !!token
  //   console.log(token)
  //   return !!token
  // }

}
