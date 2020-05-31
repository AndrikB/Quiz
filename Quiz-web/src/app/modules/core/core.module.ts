import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AuthenticationService} from './services/authentication.service';
import {AlertService} from './services/alert.service';
import {HomeComponent} from './pages/home/home.component';
import {SharedModule} from "../shared/shared.module";
import {ProfileService} from "./services/profile.service";
import {PagesRoutingModule} from "./pages/pages-routing.module";
import {CarouselModule} from "angular-bootstrap-md";
import { NotFoundComponent } from './pages/not-found/not-found.component';
import {RouterModule} from "@angular/router";

@NgModule({
  declarations: [
    HomeComponent,
    NotFoundComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    RouterModule,
    PagesRoutingModule,
    CarouselModule
  ],
  providers: [
    AlertService,
    AuthenticationService,
    ProfileService
  ]
})
export class CoreModule { }
