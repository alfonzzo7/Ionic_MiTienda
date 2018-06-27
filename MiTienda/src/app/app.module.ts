import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';

//Storage
import { IonicStorageModule } from '@ionic/storage';

//Pipe
import { ImagenPipe } from '../pipes/imagen/imagen';

import { MyApp } from './app.component';

//Paginas
import { BusquedaPage,
         CarritoPage,
         CategoriasPage,
         HomePage,
         LoginPage,
         OrdenesPage,
         OrdenesDetallePage,
         PorCategoriasPage,
         ProductoPage,
         TabsPage } from '../pages/index.paginas';

//Servicios
import { CarritoProvider, ProductosProvider, UsuarioProvider } from '../providers/index.providers';

@NgModule({
  declarations: [
    MyApp,
    ImagenPipe,
    BusquedaPage,
    CarritoPage,
    CategoriasPage,
    HomePage,
    LoginPage,
    OrdenesPage,
    OrdenesDetallePage,
    PorCategoriasPage,
    ProductoPage,
    TabsPage
  ],
  imports: [
    BrowserModule,
    HttpModule,
    IonicModule.forRoot(MyApp, {backButtonText: 'Atras'}),
    IonicStorageModule.forRoot()
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    BusquedaPage,
    CarritoPage,
    CategoriasPage,
    HomePage,
    LoginPage,
    OrdenesPage,
    OrdenesDetallePage,
    PorCategoriasPage,
    ProductoPage,
    TabsPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    CarritoProvider,
    ProductosProvider,
    UsuarioProvider
  ]
})
export class AppModule {}
