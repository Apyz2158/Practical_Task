import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from '../app.service';

@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.css'],
})
export class DisplayComponent implements OnInit {
  alldata: any[] = [];
  msg: String = '';
  constructor(private appService: AppService, public router: Router) {}

  ngOnInit() {
    localStorage.removeItem('edit');
    this.appService.GetallData().subscribe((response) => {
      console.log(response);
      this.alldata = response['body'];
      console.log(this.alldata);
    });
  }

  edit(data) {
    console.log(data);
    this.router.navigate(['home']);

    localStorage.setItem('edit', JSON.stringify(data));
  }
  delete(data) {
    console.log(data);
    this.appService.deleteById(data).subscribe((response) => {
      console.log(response);
      if (response['code'] === 200) {
        this.msg = response['message'];
        this.alldata.splice(
          this.alldata.findIndex((a) => {
            a.id === data;
          }),
          1
        );
      }
    });
  }
}
