import { environment } from './../../environments/environment';
import { Observable } from 'rxjs';
import { User } from './../login/user';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiURL: string = environment.apiURL + "/usuarios"

  constructor(
    private api: HttpClient
  ) { }


salvar(user: User) : Observable<any> {
  return this.api.post<any>(this.apiURL, user);
}

}
