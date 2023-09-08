import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateTripComponent } from './component/create-trip.component';
import { DivingService } from './service/diving.service';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DisplayTripsComponent } from './component/display-trips.component';
import { CreateAccountComponent } from './component/create-account.component';

@NgModule({
  declarations: [
    AppComponent,
    CreateTripComponent,
    DisplayTripsComponent,
    CreateAccountComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [DivingService],
  bootstrap: [AppComponent]
})
export class AppModule { }
