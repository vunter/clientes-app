import { ToastService } from './../../../toast.service';
import { AuthService } from './../../../services/auth.service';
import { User } from './../../../login/user';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {

  constructor(
    private auth: AuthService,
    private notification: ToastService) { }
  user: User;
  ngOnInit(): void {
    this.user = new User();
  }


  newUser() {

    this.auth.salvar(this.user).subscribe(
      response => {
        this.notification.showSuccess('Usuário criado com sucesso! ID do usuário: ' + response.id);
      }, errors => {
          errors.error.erros.forEach(e => {
            this.notification.showError(e);
          });
      }
    );
  }
}
