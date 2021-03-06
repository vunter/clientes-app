import { Router } from '@angular/router';
import { environment } from './../../environments/environment';
import { Observable } from 'rxjs';
import { User } from './../login/user';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import * as bcrypt from 'bcryptjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiURL: string = environment.apiURL + "/usuarios";
  tokenURL: string = environment.tokenURL;
  clientID: string = environment.clientId;
  clientSecret: string = environment.clientSecret;
  jwtHelper: JwtHelperService = new JwtHelperService();

  constructor(
    private api: HttpClient,
    private router: Router
  ) { }

  getToken() {
    let token = localStorage.getItem('access_token');
    if (token) {
      token = JSON.parse(token).access_token;
      return token;
    }
    return null;
  }

  salvar(user: User): Observable<any> {
    return this.api.post<any>(this.apiURL, user);
  }

  attemptLogin(user: string, password: string): Observable<any> {

    const params = new HttpParams()
      .set('username', user)
      .set('password', password)
      .set('grant_type', 'password');
    const headers = {
      'Authorization': 'Basic ' + btoa(`${this.clientID}:${this.clientSecret}`),
      'Content-Type': 'application/x-www-form-urlencoded'
    }
    return this.api.post(this.tokenURL, params.toString(), { headers });
  }

  logout() {
    localStorage.removeItem('access_token');
    this.router.navigate(['/login']);
  }

  getCurrentUser() {
    const token = this.getToken();
    if (token) {
      return this.jwtHelper.decodeToken(token).user_name;
    }
    return null;
  }

  isAuthenticated(): boolean {
    const token = this.getToken();

    if (token) {
      const expired = this.jwtHelper.isTokenExpired(token);
      return !expired;
    }

    return false;
  }

}
