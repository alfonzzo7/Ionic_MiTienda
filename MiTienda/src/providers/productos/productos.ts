import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';

import { URL_SERVICIOS, PRODUCTOS_PAGINA } from '../../config/url.servicios';

@Injectable()
export class ProductosProvider {

  pagina:number = 1;
  paginaPorLinea:number = 1;
  linea:number = 0;
  productos:any[] = [];
  productosPorLinea:any[] = [];
  productosResultado:any[] = [];
  lineas:any[] = [];

  constructor(public http: Http) {
    this.cargarProductos();
    this.cargarLineas();
  }

  cargarLineas(){
    let promesa = new Promise ((resolve, reject) => {
      let url = `${URL_SERVICIOS}/lineas`;

      this.http.get(url)
                .subscribe(data => {
                  if(data.ok){
                    this.lineas.push(...data.json());
                  }
                  resolve();
                });
    });

    return promesa;
  }

  cargarProductos(){
    let promesa = new Promise ((resolve, reject) => {
      let url = `${URL_SERVICIOS}/productos?pagina=${this.pagina}&limite=${PRODUCTOS_PAGINA}`;

      this.http.get(url)
                .subscribe(data => {
                  if(data.ok){
                    if(data.status == 204){
                      resolve(false);
                      return;
                    }
                    let productos = this.agrupar(data.json(), 2);
                    this.productos.push(...productos);
                    this.pagina += 1;
                  }
                  resolve(true);
                });
    });

    return promesa;
  }

  cargarProductosPorLinea(linea:any){
    let promesa = new Promise ((resolve, reject) => {
      if(linea != this.linea){
        this.linea = linea;
        this.productosPorLinea = [];
        this.paginaPorLinea = 1;
      }

      let url = `${URL_SERVICIOS}/productos/linea?linea=${linea}&pagina=${this.paginaPorLinea}&limite=${PRODUCTOS_PAGINA}`;

      this.http.get(url)
                .subscribe(data => {
                  if(data.ok){
                    if(data.json().length == 0){
                      resolve(false);
                      return;
                    }
                    this.productosPorLinea.push(...data.json());
                    this.paginaPorLinea += 1;
                  }
                  resolve(true);
                });
    });

    return promesa;
  }

  buscarProducto(termino:string) {
    let url = `${URL_SERVICIOS}/productos/busqueda?termino=${termino}`;

    this.http.get(url)
              .subscribe(data => {
                if(data.ok){
                  this.productosResultado = data.json();
                }
              });
  }

  private agrupar(arr:any[], tamano:number){
    let newArr = [];
    for(let i = 0; i < arr.length; i += tamano){
      newArr.push(arr.slice(i, i + tamano));
    }
    return newArr;
  }

}
