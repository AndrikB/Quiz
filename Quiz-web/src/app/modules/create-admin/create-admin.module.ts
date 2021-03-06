import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import {SharedModule} from "../shared/shared.module";
import {FormsModule} from "@angular/forms";
import {CreateAdminComponent} from "./pages/create-admin.component";
import {TranslateModule} from "@ngx-translate/core";

@NgModule({
  declarations: [
    CreateAdminComponent,
  ],
  imports: [
    CommonModule,
    RouterModule,
    SharedModule,
    FormsModule,
    TranslateModule
  ]
})
export class CreateAdminModule { }
