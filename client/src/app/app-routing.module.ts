import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateTripComponent } from './component/trip/create-trip.component';
import { DisplayTripsComponent } from './component/trip/display-trips.component';
import { CreateAccountComponent } from './component/account/create-account.component';
import { LoginComponent } from './component/account/login.component';
import { UpdateAccountComponent } from './component/account/update-account.component';
import { ViewMyTripsComponent } from './component/trip/view-my-trips.component';
import { ContactUsComponent } from './component/misc/contact-us.component';

const routes: Routes = [
  {path: '', component: DisplayTripsComponent, title: 'All Trips'},
  {path: 'login', component: LoginComponent, title: 'Login'},
  {path: 'trip', component: CreateTripComponent, title: 'Create New Trip'},
  {path: 'mytrip', component: ViewMyTripsComponent, title: 'View My Trips'},
  {path: 'register', component: CreateAccountComponent, title: 'Register New Account'},
  {path: 'account', component:UpdateAccountComponent, title: 'Update Account Details'},
  {path: 'contact', component:ContactUsComponent, title: 'Contact Us'},
  {path: '**', redirectTo: '/', pathMatch:'prefix'}
];

@NgModule({
  imports: [//RouterModule.forRoot(routes, { useHash: true}),
    RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
