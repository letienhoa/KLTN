import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { BusRouterServiceService } from 'src/app/services/router/bus-router-service.service';
import { ToastrService } from 'ngx-toastr';
import { BusRouter } from '@model/bus-router';
import { seat, Seat } from '@model/seats';
import { SeatServiceService } from 'src/app/services/seat/seat-service.service';
import { SetSeatDependencyFloor } from '../type-bus/bus-44';
import { Step2 } from './../../../model/book-ticket-steps';

@Component({
  selector: 'app-select-seats-two-way',
  templateUrl: './select-seats-two-way.component.html',
  styleUrls: ['./select-seats-two-way.component.css'],
})
export class SelectSeatsTwoWayComponent implements OnInit {
  price = 250000;
  totalPriceGo = 0;
  totalPriceReturn = 0;

  step1: any;
  step2s: Step2[] = [] as Step2[];

  busRouterGo: BusRouter = {} as BusRouter;
  busRouterReturn: BusRouter = {} as BusRouter;

  listTimeGo: any[] = [];
  listAMGo: any[] = [];
  listNoonGo: any[] = [];
  listPMGo: any[] = [];

  listTimeReturn: any[] = [];
  listAMReturn: any[] = [];
  listNoonReturn: any[] = [];
  listPMReturn: any[] = [];

  timeGo!: string;
  timeReturn!: string;

  seatsGo: Seat = {} as Seat;
  listSeatsChooseGo: seat[] = [] as seat[];
  listSeatsFloor1Go: any[] = [];
  listSeatsFloor2Go: any[] = [];

  seatsReturn: Seat = {} as Seat;
  listSeatsChooseReturn: seat[] = [] as seat[];
  listSeatsFloor1Return: any[] = [];
  listSeatsFloor2Return: any[] = [];

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
    if (this.listSeatsChooseGo.length == 0) {
      this.toastr.warning('Xin hãy chọn ghế xe đi');
      return;
    }

    if (this.listSeatsChooseReturn.length == 0) {
      this.toastr.warning('Xin hãy chọn ghế xe về');
      return;
    }

    let step2Go: Step2 = {
      routerId: this.busRouterGo.id,
      time: this.timeGo,
      boardingPoint: {
        id: 0,
        name: '',
      },
      seats: this.listSeatsChooseGo,
      number: this.listSeatsChooseGo.length,
      totalMoney: this.totalPriceGo,
    };

    let step2Return: Step2 = {
      routerId: this.busRouterReturn.id,
      time: this.timeReturn,
      boardingPoint: {
        id: 0,
        name: '',
      },
      seats: this.listSeatsChooseReturn,
      number: this.listSeatsChooseReturn.length,
      totalMoney: this.totalPriceReturn,
    };

    this.step2s.push(step2Go);
    this.step2s.push(step2Return);
    sessionStorage.setItem('step2', JSON.stringify(this.step2s));
    this.router.navigate(['/infor-customer']);
  }

  onChangeTime(time: any, type: number) {
    if (type == 0) {
      this.onGetSeatsStatus(
        this.busRouterGo.id,
        time.value,
        this.step1.daygo,
        type
      );
      this.listSeatsChooseGo = [];
      this.totalPriceGo = 0;
      this.timeGo = time.value;
    } else {
      this.onGetSeatsStatus(
        this.busRouterReturn.id,
        time.value,
        this.step1.returnday,
        type
      );
      this.listSeatsChooseReturn = [];
      this.totalPriceReturn = 0;
      this.timeReturn = time.value;
    }
  }

  load() {
    this.step1 = JSON.parse(sessionStorage.getItem('step1')!);
    console.log(this.step1);

    let date = this.onSetDate();

    this.onGetRouterIdById(
      this.step1.departure.id,
      this.step1.destination.id,
      this.step1.daygo,
      0
    );

    this.onGetRouterIdById(
      this.step1.destination.id,
      this.step1.departure.id,
      this.step1.returnday,
      1
    );
  }

  onBook(value: any, type: number) {
    if (type == 0) {
      var td = document.getElementById('seat');
      var dis = document.getElementsByClassName('disable');
      var tdS = document.getElementsByClassName('seatGo');

      var length = this.listSeatsChooseGo.length;

      if (dis[value.stt - 1] == null) {
        if (tdS[value.stt - 1].classList.toggle('select') == true) {
          if (length + 1 == 6) {
            tdS[value.stt - 1].classList.toggle('select');
            return alert('Chi duoc chon 5 ve');
          }
          this.listSeatsChooseGo.push(value);
          this.totalPriceGo += this.price;
        } else {
          for (let i = 0; i < length; i++) {
            if (this.listSeatsChooseGo[i].stt === value.stt) {
              this.listSeatsChooseGo.splice(i, 1);
              this.totalPriceGo -= this.price;
            }
          }
        }
      }
    } else {
      var td = document.getElementById('seat');
      var dis = document.getElementsByClassName('disable');
      var tdS = document.getElementsByClassName('seatReturn');

      var length = this.listSeatsChooseReturn.length;

      if (dis[value.stt - 1] == null) {
        if (tdS[value.stt - 1].classList.toggle('select') == true) {
          if (length + 1 == 6) {
            tdS[value.stt - 1].classList.toggle('select');
            return alert('Chi duoc chon 5 ve');
          }
          this.listSeatsChooseReturn.push(value);
          this.totalPriceReturn += this.price;
        } else {
          for (let i = 0; i < length; i++) {
            if (this.listSeatsChooseReturn[i].stt === value.stt) {
              this.listSeatsChooseReturn.splice(i, 1);
              this.totalPriceReturn -= this.price;
            }
          }
        }
      }
    }
  }

  onSetDate() {
    let day = new Date();
    let dateNow =
      ('0' + day.getDate()).slice(-2) +
      '/' +
      ('0' + (day.getMonth() + 1)).slice(-2) +
      '/' +
      day.getFullYear();
    return dateNow;
  }

  onGetRouterIdById(
    departureId: Int16Array,
    destinationId: Int16Array,
    date: string,
    type: number
  ) {
    this.routerService
      .getRouterById(departureId, destinationId)
      .subscribe((data) => {
        let routerId;
        if (type == 0) {
          this.busRouterGo = data.data;
          routerId = this.busRouterGo.id;
        } else {
          this.busRouterReturn = data.data;
          routerId = this.busRouterReturn.id;
        }
        this.onGetRunTime(routerId, date, type);
      });
  }

  onGetRunTime(routerId: Int16Array, date: string, type: number) {
    this.routerService.getRunTime(routerId, date).subscribe((data) => {
      if (type == 0) {
        this.listTimeGo = data.data;
        for (let i of data.data) {
          if (i.giochay <= 10) {
            this.listAMGo.push(i);
          }
          if (i.giochay > 10 && i.giochay < 17) {
            this.listNoonGo.push(i);
          }
          if (i.giochay >= 17) {
            this.listPMGo.push(i);
          }
        }
        this.timeGo = data.data[0].giochay;
      } else {
        this.listTimeReturn = data.data;
        for (let i of data.data) {
          if (i.giochay <= 10) {
            this.listAMReturn.push(i);
          }
          if (i.giochay > 10 && i.giochay < 17) {
            this.listNoonReturn.push(i);
          }
          if (i.giochay >= 17) {
            this.listPMReturn.push(i);
          }
        }
        this.timeReturn = data.data[0].giochay;
      }

      this.onGetSeatsStatus(routerId, data.data[0].giochay, date, type);
    });
  }

  onGetSeatsStatus(
    routerId: Int16Array,
    time: string,
    date: string,
    type: number
  ) {

    this.seatsService.getStatusSeat(routerId, time, date).subscribe((data) => {
     
      let listSeat = SetSeatDependencyFloor(data.data);
      if (type == 0) {
        this.seatsGo = data.data;
        this.listSeatsFloor1Go = listSeat[0];
        if (listSeat.length > 1) {
          this.listSeatsFloor2Go = listSeat[1];
        }
        console.log("list seat go");
        console.log(this.seatsGo);
      } else {
        
        this.seatsReturn = data.data;
        console.log("list seat return");
        console.log(this.seatsReturn);
        this.listSeatsFloor1Return = listSeat[0];
        if (listSeat.length > 1) {
          this.listSeatsFloor2Return = listSeat[1];
        }
      }
    });
  }
}
