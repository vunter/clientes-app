import { AdminHomeComponent } from './admin-home/admin-home/admin-home.component';
import { AdminComponent } from './admin/admin.component';
import { MainComponent } from './../main/main.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: 'admin', component: MainComponent, children: [
      { path: '', component: AdminHomeComponent},
      {path: 'newUser', component: AdminComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
