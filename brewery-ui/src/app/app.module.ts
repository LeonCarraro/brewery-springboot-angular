import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { RadioButtonModule } from 'primeng/radiobutton';
import { TableModule } from 'primeng/table';
import { CardModule } from 'primeng/card';

import { AppComponent } from './app.component';
import { TopbarComponent } from './components/topbar/topbar.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { BeerSearchComponent } from './components/beer-search/beer-search.component';
import { BeerTableComponent } from './components/beer-table/beer-table.component';

@NgModule({
  declarations: [
    AppComponent,
    TopbarComponent,
    SidebarComponent,
    BeerSearchComponent,
    BeerTableComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    ButtonModule,
    InputTextModule,
    DropdownModule,
    RadioButtonModule,
    TableModule,
    CardModule
  ],
  providers: [
    AppComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
