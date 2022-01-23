import { UsuarioService } from './../../../service/usuario.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {

  students : Observable<User[]>;
  nome : String;

  constructor(private UsuarioService: UsuarioService) { 

  }

  ngOnInit() {

    this.UsuarioService.getStudentList().subscribe(data => {
      this.students = data;
    });
    
  }

  deleteUsuario(id:Number){
    this.UsuarioService.deletarUsuario(id).subscribe(data => {
      console.log("Deletado com sucesso : " + data);
      this.UsuarioService.getStudentList().subscribe(data => {
      this.students = data;
      });
    })
  }

  consultarUser(){
    this.UsuarioService.consultarUser(this.nome).subscribe(data => {
      this.students = data;
    })
  }
}