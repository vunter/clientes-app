import { ToastService } from './../../toast.service';
import { ServicoPrestadoService } from './../../services/servicos-prestados/servico-prestado.service';
import { FiltroPesquisaServico } from '../filtro-pesquisa-servico';
import { ServicoPrestado } from './../servico-prestado';
import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/clientes/cliente';

@Component({
  selector: 'app-servico-prestado-list',
  templateUrl: './servico-prestado-list.component.html',
  styleUrls: ['./servico-prestado-list.component.css']
})
export class ServicoPrestadoListComponent implements OnInit {

  servicosPrestado: ServicoPrestado[];
  filtroPesquisa: FiltroPesquisaServico;
  servicoPrestado: ServicoPrestado;

  constructor(private service: ServicoPrestadoService,
    private notification: ToastService) {
    this.filtroPesquisa = new FiltroPesquisaServico();
    this.servicosPrestado = [];
    this.servicoPrestado = new ServicoPrestado();
    this.servicoPrestado.cliente = new Cliente();
  }

  ngOnInit(): void { }


  onSubmit() {
    this.service.buscaPorFiltro(this.filtroPesquisa)
      .subscribe(
        response => {
          this.servicosPrestado = response;

          if (!response.length) {
            this.notification.showWarning('Não foi encontrado nenhum dado com estas informações!', 5000);
          }
        }
      )
  }

  clearInput() {
    this.filtroPesquisa = new FiltroPesquisaServico();
  }

  visualizarDetalhes(servicoPrestado: ServicoPrestado) {
    this.servicoPrestado = servicoPrestado;
  }

}
