import { MainComponent } from './../main/main.component';
import { ClientesListComponent } from './clientes-list/clientes-list.component';
import { ClientesFormComponent } from './clientes-form/clientes-form.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { path: 'clientes', component: MainComponent, children: [
    { path: 'cadastro', component: ClientesFormComponent },
    { path: 'cadastro/:id', component: ClientesFormComponent },
    { path: 'listar', component: ClientesListComponent},
    { path: '', redirectTo: '/clientes/listar', pathMatch: 'full' }
  ]}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientesRoutingModule { }
