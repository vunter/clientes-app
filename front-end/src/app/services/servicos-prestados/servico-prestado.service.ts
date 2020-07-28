import { FiltroPesquisaServico } from '../../servicos/filtro-pesquisa-servico';
import { environment } from './../../../environments/environment';
import { Observable } from 'rxjs';
import { ServicoPrestado } from './../../servicos/servico-prestado';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ServicoPrestadoService {

  apiURL: string = environment.apiURL + '/servicos/';

  constructor(private api: HttpClient) { }

  salvar(servicoPrestado: ServicoPrestado): Observable<ServicoPrestado> {
      return this.api.post<ServicoPrestado>(this.apiURL + 'salvar', servicoPrestado);
  }

  buscaPorFiltro(filtroPesquisa: FiltroPesquisaServico): Observable<ServicoPrestado[]> {
    const httpParams = new HttpParams();
    httpParams.set("servicoPrestadoFiltroDTO", JSON.stringify(filtroPesquisa));

    return this.api.post<ServicoPrestado[]>(this.apiURL + 'buscar', filtroPesquisa);
  }
}
