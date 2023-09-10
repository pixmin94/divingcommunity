import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateTripComponent } from './component/trip/create-trip.component';
import { DisplayTripsComponent } from './component/trip/display-trips.component';
import { CreateAccountComponent } from './component/account/create-account.component';
import { LoginComponent } from './component/account/login.component';

const routes: Routes = [
  {path: '', component: DisplayTripsComponent, title: 'All Trips'},
  {path: 'login', component: LoginComponent, title: 'Login'},
  {path: 'trip', component: CreateTripComponent, title: 'Create New Trip'},
  {path: 'register', component: CreateAccountComponent, title: 'Register New Account'},
  {path: '**', redirectTo: '/', pathMatch:'prefix'}
];

@NgModule({
  imports: [//RouterModule.forRoot(routes, { useHash: true}),
    RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
