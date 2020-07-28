import { MainComponent } from './../main/main.component';
import { ServicoPrestadoListComponent } from './servico-prestado-list/servico-prestado-list.component';
import { ServicoPrestadoFormComponent } from './servico-prestado-form/servico-prestado-form.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {
    path: 'servicos', component: MainComponent, children: [
      { path: 'form', component: ServicoPrestadoFormComponent },
      { path: 'list', component: ServicoPrestadoListComponent },
      { path: '', redirectTo: '/servicos/list', pathMatch: 'full'}
    ]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ServicoPrestadoRoutingModule { }
