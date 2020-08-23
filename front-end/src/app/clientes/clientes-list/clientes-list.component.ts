import { ToastService } from './../../toast.service';
import { ClientesService } from './../../services/clientes/clientes.service';
import { Cliente } from './../cliente';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-clientes-list',
  templateUrl: './clientes-list.component.html',
  styleUrls: ['./clientes-list.component.css']
})
export class ClientesListComponent implements OnInit {

  clientes: Cliente[] = [];
  clienteSelecionado: Cliente;
  errors: String[] = [];
  mensagemSucesso: String;

  constructor(
    private service: ClientesService,
    private router: Router,
    private notification: ToastService) {  }

  ngOnInit(): void {
    this.clienteSelecionado = new Cliente();
    this.service
    .listClientes().subscribe( response => {
      this.clientes = response;
    }, errorResponse => {
      this.errors = errorResponse.error.erros;
    });
  }

  novoCadastro() {
    this.router.navigate(['/clientes/cadastro']);
  }

  preparaDeletar(cliente: Cliente) {
    this.clienteSelecionado = cliente;
  }

  deletarCliente() {
    this.service
    .deletarCliente(this.clienteSelecionado)
    .subscribe(reponse => {
      this.notification.showSuccess('Cliente deletado com sucesso!');

      this.ngOnInit();
    }, errorResponse => {
      this.errors = errorResponse.error.erros;
    });

  }

}
