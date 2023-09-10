import { DivingService } from './../../service/diving.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { User } from 'src/app/model/models';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  form!: FormGroup

  constructor( private fb: FormBuilder,
      private service: DivingService) { }

  login() {
    const user: User = this.form.value
    // this.service.login(user)
    //   .subscribe({
    //     next: x => console.log(x),
    //     error: err => console.log(err),
    //     complete: () => console.log("done")
    //   })
    this.service.login(user).subscribe(
      res => {
        alert("Logged into "+res)
        this.form.reset()
        this.service.setLoggedIn(true)
      }
    )
  }

  ngOnInit(): void {
    this.form = this.createForm()
  }

  createForm() {
    return this.fb.group({
      username: this.fb.control<string>('', [Validators.required]),
      password: this.fb.control<string>('', [Validators.required])
      })
  }
}
