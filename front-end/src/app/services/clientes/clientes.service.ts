import { environment } from './../../../environments/environment';
import { Cliente } from './../../clientes/cliente';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {

  apiURL: string = environment.apiURL + '/cliente';

  constructor(private api : HttpClient) {

  }

  salvar(cliente: Cliente) : Observable<Cliente> {
    return this.api.post<Cliente>(`${this.apiURL}/salvar`, cliente);
  }

  getCliente(): Cliente {
    let cliente : Cliente = new Cliente();
    cliente.nome = 'Leonardo';
    cliente.cpf = '06742100184';
    return cliente;
  }
  getClienteById(id: number): Observable<Cliente> {
    return this.api.get<any>(`${this.apiURL}/${id}`);
  }

  listClientes(): Observable<Cliente[]> {
    return this.api.get<Cliente[]>(`${this.apiURL}/clientes`);
  }

  atualizar(cliente: Cliente) : Observable<any> {
    return this.api.put<any>(`${this.apiURL}/update/${cliente.id}`, cliente);
  }

  deletarCliente(cliente: Cliente): Observable<any>{
      return this.api.delete<any>(`${this.apiURL}/delete/${cliente.id}`);
  }

}
