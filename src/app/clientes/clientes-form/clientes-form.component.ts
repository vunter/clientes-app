import { ToastService } from './../../toast.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ClientesService } from './../../services/clientes/clientes.service';
import { Cliente } from './../cliente';
import { Component, OnInit } from '@angular/core';
import * as moment from 'moment';

@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css'],
})
export class ClientesFormComponent implements OnInit {
  moment = moment();
  success: boolean = false;
  errors: String[];
  cliente: Cliente;

  constructor(
    private service: ClientesService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private notification: ToastService
  ) {
    this.cliente = new Cliente();
  }

  ngOnInit(): void {
    let params;

    this.activatedRoute.params.subscribe((parameters) => {
      params = parameters;
    });

    if (params && params.id) {
      let id: number = params.id;
      this.service.getClienteById(id).subscribe(
        (response) => {
          this.cliente = response;
        },
        (errorResponse) => {
          this.cliente = new Cliente();
          this.errors = errorResponse.error.erros;
        }
      );
    }
  }

  onSubmit() {
    if (!this.cliente.id) {
      this.service.salvar(this.cliente).subscribe(
        (response) => {
          console.log(response);
          this.success = true;
          this.notification.showSuccessTitle('Cliente salvo com sucesso!', 'Sucesso', 5000);
          this.cliente = response;
          this.errors = [];
        },
        (errorResponse) => {
          this.success = false;
          this.errors = errorResponse.error.erros;
          this.errors.forEach( (item) => {
          this.notification.showErrorTitle('Ocorreu um erro ao salvar o cliente! ' + item, 'Erro!', 5000);
        });
        }
      );
    } else {
      this.service
      .atualizar(this.cliente)
      .subscribe(response => {
        this.success = true;
        this.notification.showSuccessTitle('Cliente atualizado com sucesso!', 'Sucesso', 5000);
        this.errors = null;
      }, errorResponse => {
        this.errors = errorResponse.error.erros;
        this.errors.forEach( (item) => {
          this.notification.showErrorTitle('Ocorreu um erro ao atualizar o cliente! ' + item, 'Erro!', 5000);
        });
      })
    }
  }

  redirectListagem() {
    this.router.navigate(['/clientes/listar']);
  }

}
