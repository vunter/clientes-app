import { Cliente } from './../clientes/cliente';
export class ServicoPrestado {
  id: number;
  descricao: string;
  idCliente: number;
  valor: number;
  data: string;
  cliente: Cliente;

 }
