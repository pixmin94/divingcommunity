import { CookieService } from 'ngx-cookie-service';
import { DivingService } from './../../service/diving.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Account } from 'src/app/model/models';

@Component({
  selector: 'app-update-account',
  templateUrl: './update-account.component.html',
  styleUrls: ['./update-account.component.css']
})
export class UpdateAccountComponent implements OnInit{
  constructor (
    private service: DivingService,
    private cookieSvc: CookieService,
    private fb: FormBuilder
  ) { }

  form!: FormGroup
  username!: string

  ngOnInit(): void {
    this.username = this.cookieSvc.get('username')
    // console.log(this.username)

    if(!this.username)
      this.form = this.createForm()

    this.service.findUsername(this.username)
      .then(account => {
        this.form = this.createForm(account)
      })
  }

  submitAccount() {
    const account: Account = this.form.value
    this.service.updateAccount(account)
      .then(result => alert("Updated account: "+account.username))
      .catch(error => alert("Error: "+error))
  }

  createForm(data: Account | null = null) {
    return this.fb.group({
      password: this.fb.control<string>(data? '' : ''),
      fullName: this.fb.control<string>(data? data.fullName: ''),
      email: this.fb.control<string>(data? data.email: '', [Validators.email]),
      nationality: this.fb.control<string>(data? data.nationality: '')
    })
  }
}
