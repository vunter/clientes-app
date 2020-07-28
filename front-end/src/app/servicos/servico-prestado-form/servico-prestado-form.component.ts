import { ToastService } from './../../toast.service';
import { ServicoPrestadoService } from './../../services/servicos-prestados/servico-prestado.service';
import { ServicoPrestado } from './../servico-prestado';
import { ClientesService } from './../../services/clientes/clientes.service';
import { Cliente } from './../../clientes/cliente';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-servico-prestado-form',
  templateUrl: './servico-prestado-form.component.html',
  styleUrls: ['./servico-prestado-form.component.css']
})
export class ServicoPrestadoFormComponent implements OnInit {

  clientes: Cliente[];
  servico: ServicoPrestado;
  errors: string[];

  constructor(
    private clientesService: ClientesService,
    private servicoService: ServicoPrestadoService,
    private notification: ToastService
    ) {
    this.servico = new ServicoPrestado();
  }

  ngOnInit(): void {

    this.clientesService
    .listClientes()
    .subscribe(
      response => {
        if (response.length) {
        this.clientes = response
      } else {
          this.notification.showWarning('Não existe nenhum cliente cadastrado')
      }
      },
      errorResponse => this.notification.showWarning(errorResponse.error.erros)
    );

  }

  onSubmit() {
    console.log(this.servico)
    this.servicoService
    .salvar(this.servico)
    .subscribe(
     response =>  {
       this.servico = response
        this.notification.showSuccess('Salvo com sucesso!')
     },
     errorResponse => {
      this.errors = errorResponse.error.erros;
      this.errors.forEach( (item) => {
        this.notification.showError('Erro! ' + item, 10000);
      });

     }
    );
      console.log(this.servico)
  }

}
