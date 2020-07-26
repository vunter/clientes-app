import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ServicoPrestadoFormComponent } from './servico-prestado-form/servico-prestado-form.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ServicoPrestadoRoutingModule } from './servico-prestado-routing.module';
import { ServicoPrestadoListComponent } from './servico-prestado-list/servico-prestado-list.component';


@NgModule({
  declarations: [
    ServicoPrestadoListComponent,
    ServicoPrestadoFormComponent
  ],
  imports: [
    CommonModule,
    ServicoPrestadoRoutingModule,
    RouterModule,
    BrowserModule,
    FormsModule
  ],
  exports: [
    ServicoPrestadoFormComponent,
    ServicoPrestadoListComponent
  ]
})
export class ServicoPrestadoModule { }
