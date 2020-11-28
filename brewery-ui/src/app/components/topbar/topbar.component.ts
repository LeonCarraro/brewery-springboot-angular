import { Component, HostListener, OnInit } from '@angular/core';
import { AppComponent } from '../../app.component';

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.css']
})
export class TopbarComponent implements OnInit {

  screenWidth: number;
  openMenu = false;

  constructor(
    private appComponent: AppComponent
  ) { }

  ngOnInit(): void {
    this.getScreenWidth();
  }

  @HostListener('window:resize', ['$event'])
  onResize() {
    this.getScreenWidth();
  }

  onOpenMenu() {
    this.openMenu = !this.openMenu;
    this.appComponent.resizeScreen = this.openMenu;
  }

  getScreenWidth() {
    this.screenWidth = window.innerWidth;
    if (this.screenWidth >= 980) {
      this.openMenu = true;
      this.appComponent.resizeScreen = true;
      this.appComponent.minimumScreenWidth = true;
    } else {
      if (this.appComponent.minimumScreenWidth === true) {
        this.openMenu = false;
        this.appComponent.resizeScreen = false;
        this.appComponent.minimumScreenWidth = false;
      }
    }
  }

}
