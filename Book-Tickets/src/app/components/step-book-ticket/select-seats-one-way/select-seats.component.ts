import { BusRouter } from 'src/app/model/bus-router';
import { seat } from './../../../model/seats';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { SeatServiceService } from 'src/app/services/seat/seat-service.service';
import { Seat } from 'src/app/model/seats';
import { BusRouterServiceService } from 'src/app/services/router/bus-router-service.service';
import { SetSeatDependencyFloor } from './../type-bus/bus-44';
import { ToastrService } from 'ngx-toastr';
import { Step2 } from "./../../../model/book-ticket-steps";

@Component({
  selector: 'app-select-seats',
  templateUrl: './select-seats.component.html',
  styleUrls: ['./select-seats.component.css'],
})
export class SelectSeatsComponent implements OnInit {
  step1: any;

  date!: string;
  time!: string;
  floors = 2;

  totalPrice = 0;

  listTime: any[] = [];
  listAM: any[] = [];
  listNoon: any[] = [];
  listPM: any[] = [];

  timeIndex = 1;

  seats: Seat = {} as Seat;

  listSeatsChoose: seat[] = [] as seat[];
  listSeatsFloor1: any[] = [];
  listSeatsFloor2: any[] = [];

  busRouter: BusRouter = {} as BusRouter;
  step2: Step2 = {} as Step2;
  constructor(
    private router: Router,
    private seatsService: SeatServiceService,
    private routerService: BusRouterServiceService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.load();
  }

  onContinue() {
    if (this.listSeatsChoose.length == 0) {
      this.toastr.warning('Xin hay chon ghe');
      return;
    }

    this.step2 = {
      routerId : this.busRouter.id, 
      time : this.time,
      boardingPoint:{
        id:0,
        name:""
      },
      seats: this.listSeatsChoose,
      number: this.listSeatsChoose.length,
      totalMoney: this.totalPrice
    };
    sessionStorage.setItem("step2", JSON.stringify(this.step2));
    this.router.navigate(['/infor-customer']);
  }

  onChangeTime(time: any) {
    this.time = time.value;
    this.onGetSeatsStatus(this.busRouter.id, time.value, this.step1.daygo);
    this.listSeatsChoose = [];
    this.totalPrice = 0;
  }

  load() {
    let day = new Date();
    this.date =
      ('0' + day.getDate()).slice(-2) +
      '/' +
      ('0' + (day.getMonth() + 1)).slice(-2) +
      '/' +
      day.getFullYear();  

    this.step1 = JSON.parse(sessionStorage.getItem('step1')!);
    console.log('Step1');
    console.log(this.step1);

    this.onGetRouterIdById(this.step1.departure.id, this.step1.destination.id);
  }

  onBook(value: any) {
    var td = document.getElementById('seat');
    var dis = document.getElementsByClassName('disable');
    var tdS = document.getElementsByClassName('seat');

    var length = this.listSeatsChoose.length;

    if (dis[value.stt - 1] == null) {
      if (tdS[value.stt - 1].classList.toggle('select') == true) {
        if (length + 1 == 6) {
          tdS[value.stt - 1].classList.toggle('select');
          return alert('Chi duoc chon 5 ve');
        }
        this.listSeatsChoose.push(value);
        this.totalPrice += this.busRouter.gia_ca;
      } else {
        for (let i = 0; i < length; i++) {
          if (this.listSeatsChoose[i].stt === value.stt) {
            this.listSeatsChoose.splice(i, 1);
            this.totalPrice -= this.busRouter.gia_ca;
          }
        }
      }
    }
  }

  onGetSeatsStatus(routerId: Int16Array, time: string, date: string) {
    this.seatsService.getStatusSeat(routerId, time, date).subscribe((data) => {
      this.seats = data.data;
      let listSeat = SetSeatDependencyFloor(this.seats);
      this.listSeatsFloor1 = listSeat[0];
      if(listSeat.length>1){
        this.listSeatsFloor2 = listSeat[1];
      }
    });
  }

  onGetRouterIdById(departureId: Int16Array, destinationId: Int16Array) {
    this.routerService
      .getRouterById(departureId, destinationId)
      .subscribe((data) => {
        this.busRouter = data.data;
        this.onGetRunTime(this.busRouter.id, this.step1.daygo);
      });
  }

  onGetRunTime(routeId: Int16Array, date: string) {
    this.routerService.getRunTime(routeId, date).subscribe((data) => {
      this.listTime = data.data;
      for (let i of data.data) {
        if (i.giochay <= 10) {
          this.listAM.push(i);
        }
        if (i.giochay > 10 && i.giochay < 17) {
          this.listNoon.push(i);
        }
        if (i.giochay >= 17) {
          this.listPM.push(i);
        }
      }
      this.time = data.data[0].giochay;
      this.onGetSeatsStatus(routeId, data.data[0].giochay, date);
    });
  }
}
