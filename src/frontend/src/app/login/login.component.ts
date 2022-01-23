import { LoginServiceService } from './../service/login-service.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usuario = {login: '', senha: ''};

  constructor(private loginService: LoginServiceService){}

  public login (){

    this.loginService.login(this.usuario);
  }

  ngOnInit() {
  }

}
