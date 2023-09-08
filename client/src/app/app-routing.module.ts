import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateTripComponent } from './component/create-trip.component';
import { DisplayTripsComponent } from './component/display-trips.component';
import { CreateAccountComponent } from './component/create-account.component';

const routes: Routes = [
  {path: '', component: DisplayTripsComponent, title: 'All Trips'},
  {path: 'create-trip', component: CreateTripComponent, title: 'Create New Trip'},
  {path: 'create-account', component: CreateAccountComponent, title: 'Register New Account'},
  {path: '**', redirectTo: '/', pathMatch:'prefix'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
