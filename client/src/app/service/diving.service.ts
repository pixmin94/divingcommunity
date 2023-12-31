import { logout } from './../store/user.actions';
import { Store } from '@ngrx/store';
import { Account, Trip, User } from './../model/models';
import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { CookieService } from 'ngx-cookie-service';
import { BehaviorSubject, Observable, firstValueFrom } from 'rxjs';
import * as UserActions from '../store/user.actions';

@Injectable()
export class DivingService {
  constructor(
    private http: HttpClient,
    private cookieSvc: CookieService,
    private store: Store
    ) {  }

  createTrip(trip: Trip, image: File): Promise<any> {
    const formData: FormData = new FormData()

    formData.append('title', trip.title)
    formData.append('location', trip.location)
    formData.append('startDate', trip.startDate.toString())
    formData.append('endDate', trip.endDate.toString())
    formData.append('image', image)

    return firstValueFrom(
      this.http.post('/api/createtrip', formData)
    )
  }

  getTrips(): Observable<Trip[]> {
    return this.http.get<any>('/api/gettrips')
  }

  getMyTrips(): Observable<any> {
    const username = this.cookieSvc.get('username')
    const params = new HttpParams()
      .set("username", username)
    return this.http.get('/api/getmytrips', { params })
  }

  createAccount(account: Account): Observable<any> {
    return this.http.post('/api/createaccount', account, {responseType: 'text'})

  }

  updateAccount(account: Account): Promise<any> {
    account.username = this.cookieSvc.get('username')
    return firstValueFrom(
      this.http.post<Account>('/api/updateaccount', account)
    )
  }

  login(user: User): Observable<any> {
    this.store.dispatch(UserActions.login())
    console.log("logging in service: "+user.username)
    return this.http.post('/api/login', user, {responseType: 'text'})
  }

  logout(){
    this.store.dispatch(UserActions.logout())
    this.cookieSvc.delete('username')
  }

  initUserState() {
    const userState = this.cookieSvc.check('username')
    if(userState) {
      this.store.dispatch(UserActions.login())
    }
  }

  // private isLoggedInSubj = new BehaviorSubject<boolean>(false)
  // isLoggedIn$ = this.isLoggedInSubj.asObservable()
  // setLoggedIn(isLoggedIn: boolean) {
  //   this.isLoggedInSubj.next(isLoggedIn)
  // }

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
      this.http.get('/api/jointrip', { params, responseType: 'text' })
    )
  }

  public leaveTrip(tripId: string, username: string): Promise<any> {
    const params = new HttpParams()
      .set("tripId", tripId)
      .set("username", username)
    return firstValueFrom(
      this.http.get('/api/leavetrip', { params, responseType: 'text' })
    )
  }

}
