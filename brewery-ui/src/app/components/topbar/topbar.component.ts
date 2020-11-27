import { Component, HostListener, OnInit } from '@angular/core';

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.css']
})
export class TopbarComponent implements OnInit {

  screenWidth: number;
  openMenu = false;

  constructor() { }

  ngOnInit(): void {
    this.getScreenWidth();
  }

  @HostListener('window:resize', ['$event'])
  onResize() {
    this.getScreenWidth();
  }

  getScreenWidth() {
    this.screenWidth = window.innerWidth;
  }

}
