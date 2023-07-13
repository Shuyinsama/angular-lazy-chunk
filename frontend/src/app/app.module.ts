import {APP_BASE_HREF} from '@angular/common';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LOCALE_ID } from '@angular/core';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    {
      provide: APP_BASE_HREF,
      useFactory: (locale: string) => `/js/dist/${locale}/`,
      deps: [LOCALE_ID]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
