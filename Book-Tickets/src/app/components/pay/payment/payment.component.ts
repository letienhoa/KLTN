import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

declare var $:any

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {  
  inforCustomer:any;
  inforRoute:any;
  inforSeat: any;

  isSelectTypePay = false;
  type!:number;

  constructor(private router:Router,
    private toastr: ToastrService) { }

  ngOnInit(): void {
    this.inforCustomer = JSON.parse(sessionStorage.getItem("step3")!);
    this.inforSeat = JSON.parse(sessionStorage.getItem("step2")!);
    this.inforRoute = JSON.parse(sessionStorage.getItem("step1")!);
    console.log(this.inforRoute);
    console.log(this.inforSeat);
    console.log(this.inforCustomer);
  }

  onSelectTypePay(type: number){
    this.type = type;
    this.isSelectTypePay = true;
    let pay = <HTMLCollection>document.getElementsByClassName("pay-item");
    let length = pay.length;
    for(let i = 0; i< length; i++){
      pay[i].classList.remove('pay-item-click');
    }
    pay[type].classList.add('pay-item-click');
  }

  onContinue(){
    if(!this.isSelectTypePay){
      this.toastr.warning("Xin hãy chọn hình thức thanh toán!");
      return;
    }

    let ticketInfor = {
      routerId: this.inforSeat.routerId,
      customerName: this.inforCustomer.name,
      phone: this.inforCustomer.phone,
      email: this.inforCustomer.email,
      ticketOneWay: {
        departure: this.inforRoute.departure,
        destination: this.inforRoute.destination,
        runTime: this.inforSeat.time,
        seats: this.inforSeat.seats,
        totalMoney: this.inforSeat.totalMoney,
        dayGo: this.inforRoute.daygo
      },
      ticketTwoWay: {
        router: "",
        runTime: "",
        seats: [],
        totalMoney: ""
      }
    }

    sessionStorage.setItem("TicketInfor", JSON.stringify(ticketInfor));
    $('#myModalPay').show();
  }
}

$(document).mouseup(function(e:any) 
{
  var container = $("#myModalPay");

  if (container.is(e.target)) 
  {
    $('#myModalPay').hide();
  }
});