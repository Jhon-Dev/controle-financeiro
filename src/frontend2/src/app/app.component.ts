import { Component } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend';
  constructor (private router: Router){

  }

  ngOnInit(): void {

    if(localStorage.getItem('token') == null) {
        this.router.navigate(['login']);
    }
  }

  public sair(){
    localStorage.clear();
    this.router.navigate(['login']);
  }
}
