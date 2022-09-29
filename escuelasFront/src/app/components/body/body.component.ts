import { Component, OnInit } from '@angular/core';
import { EscuelasService } from 'src/app/services/escuelas.service';

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {

  escuelas: any;
  arrEscuela: any;

  constructor( private _escuelas: EscuelasService) { }

  ngOnInit(): void {
    this.obtenerEscuelas();
  }
  obtenerEscuelas(){
    this._escuelas.obtenerEscuelas()
      .subscribe(respuesta=>{
        this.escuelas = respuesta;
        this.arrEscuela = this.escuelas.escuelaResponse.escuelas;
          console.log(this.arrEscuela)
      })
  }

}
