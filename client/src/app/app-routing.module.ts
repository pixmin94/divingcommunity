import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateTripComponent } from './component/create-trip.component';

const routes: Routes = [
  {path: '', component: CreateTripComponent, title: 'Main'},
  {path: '**', redirectTo: '/', pathMatch:'prefix'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
