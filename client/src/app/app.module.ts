import { NgModule, isDevMode } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateTripComponent } from './component/trip/create-trip.component';
import { DivingService } from './service/diving.service';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DisplayTripsComponent } from './component/trip/display-trips.component';
import { CreateAccountComponent } from './component/account/create-account.component';
import { RouterModule } from '@angular/router';
import { LoginComponent } from './component/account/login.component';
import { CookieService } from 'ngx-cookie-service';
import { UpdateAccountComponent } from './component/account/update-account.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { StoreModule } from '@ngrx/store';
import { userReducer } from './store/user.reducer';
import { ViewMyTripsComponent } from './component/trip/view-my-trips.component';
import { NavbarComponent } from './component/misc/navbar.component';
import { FooterComponent } from './component/misc/footer.component';
import { ContactUsComponent } from './component/misc/contact-us.component';
import { ServiceWorkerModule } from '@angular/service-worker';


@NgModule({
  declarations: [
    AppComponent,
    CreateTripComponent,
    DisplayTripsComponent,
    CreateAccountComponent,
    NavbarComponent,
    LoginComponent,
    UpdateAccountComponent,
    ViewMyTripsComponent,
    FooterComponent,
    ContactUsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NoopAnimationsModule,
    MaterialModule,
    StoreModule.forRoot({user: userReducer}),
    ServiceWorkerModule.register('ngsw-worker.js', {
      enabled: !isDevMode(),
      // Register the ServiceWorker as soon as the application is stable
      // or after 30 seconds (whichever comes first).
      registrationStrategy: 'registerWhenStable:30000'
    })
  ],
  providers: [
    DivingService,
    CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
