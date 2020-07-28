import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login: LoginForm;
  loginError: boolean;
  constructor() { }

  ngOnInit(): void {
  }

  onSubmit() {
      console.log(this.login.username)
  }

}

class LoginForm {
  username: string;
  password: string;
}
