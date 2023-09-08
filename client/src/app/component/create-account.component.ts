import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DivingService } from '../service/diving.service';
import { Account } from '../model/models';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent {
  form!: FormGroup
  tripID!: number

  constructor(private fb: FormBuilder,
      private service: DivingService) { }

  ngOnInit(): void {
    this.form = this.createForm()
  }

  submitTrip() {
    const account: Account = this.form.value
    // console.log(trip)
    this.service.createAccount(account)
      .then(result => alert("User created: "+result))
      .catch(error => alert("Error: "+error))
  }

  createForm() {
    return this.fb.group({
      username: this.fb.control<string>('', [Validators.required]),
      fullName: this.fb.control<string>('', [Validators.required]),
      email: this.fb.control<string>('', [Validators.required, Validators.email]),
      nationality: this.fb.control<string>('')
      // image: this.fb.control<string>('')

    })
  }
}
