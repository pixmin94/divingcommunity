import { Account, Trip, User } from './../model/models';
import { HttpClient, HttpParams } from "@angular/common/http";
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

  public findUsername(username: string): Promise<any> {
    const params = new HttpParams()
      .set("username", username)
    return firstValueFrom(
      this.http.get<Account>('/api/finduser', { params })
    )
  }

  public joinTrip(tripId: string, username: string) : Promise<any> {
    const params = new HttpParams()
      .set("tripId", tripId)
      .set("username", username)
    return firstValueFrom(
      this.http.get<any>('/api/jointrip', { params })
    )
  }

  // getAttendees()

}
