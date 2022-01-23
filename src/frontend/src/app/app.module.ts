import { FooterComponent } from './componetes/template/footer/footer.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms'
import { HttpClientModule, HttpInterceptor } from '@angular/common/http';
import { HeaderComponent } from './componetes/template/header/header.component';  /*Para fazer as Requisicoes Ajax */
import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from '@angular/compiler/src/core';
import { LoginComponent } from './login/login.component';
import { HttpInterceptorModule } from './service/header-interceptor.service';
import { UsuarioComponent } from './componetes/usuario/usuario/usuario.component';
import { UsuarioAddComponent } from './componetes/usuario/usuario/usuario-add/usuario-add/usuario-add.component';

import { MatListModule, MatSidenavModule, MatToolbarModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavComponent } from './componetes/template/nav/nav.component';



export const appRouters: Routes = [

  {path : 'header', component : HeaderComponent},
  {path : 'login', component : LoginComponent},
  {path : '', component : LoginComponent},
  {path : 'userList', component: UsuarioComponent },
  {path: 'usuarioAdd', component : UsuarioAddComponent},
  {path: 'usuarioAdd/:id', component : UsuarioAddComponent},

];

export const routes : ModuleWithProviders = RouterModule.forRoot(appRouters)

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    UsuarioComponent,
    UsuarioAddComponent,
    NavComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    routes,
    HttpInterceptorModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
