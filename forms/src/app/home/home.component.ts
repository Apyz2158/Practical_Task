import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppService } from '../app.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  AddSubscriber: FormGroup;
  data: any;
  edit: boolean = false;
  isMale: boolean = false;

  editdata: any[] = [];

  constructor(
    private http: HttpClient,
    private appService: AppService,
    public router: Router
  ) {}

  ngOnInit() {
    this.AddSubscriber = new FormGroup({
      name: new FormControl(null, Validators.required),
      gender: new FormControl(null, Validators.required),
      email: new FormControl(null, Validators.required),
      mobile: new FormControl(null, Validators.required),
      addressLine1: new FormControl(null, Validators.required),
      addressLine2: new FormControl(null, Validators.required),
      city: new FormControl(null, Validators.required),
      state: new FormControl(null, Validators.required),
      country: new FormControl(null, Validators.required),
      zipcode: new FormControl(null, Validators.required),
    });

    this.data = JSON.parse(localStorage.getItem('edit') || '[]');
    console.log(this.data);

    if (this.data.length != 0) {
      this.edit = true;

      if (this.data['gender'] == 'MALE') {
        this.isMale = true;
      } else {
        this.isMale = false;
      }

      console.log(this.edit);
      console.log(this.isMale);
    }
  }

  Subscriber() {
    console.log('hello');
    console.log(this.AddSubscriber.value);

    this.appService.sendData(this.AddSubscriber.value).subscribe(
      (response) => {
        console.log(response);
        if (response['code'] == 200) {
          this.router.navigate(['display']);
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }

  Subscribers() {
    this.editdata = [this.AddSubscriber.value];
    this.editdata.forEach((a) => {
      a.id = this.data['id'];
    });
    console.log(this.editdata);

    this.appService.editData(this.editdata[0]).subscribe((res) => {
      console.log(res);
      if (res['code'] == 200) {
        this.router.navigate(['display']);
      }
    });
  }
}
