import { Component, OnInit } from '@angular/core';
import { ElementRef, ViewChild } from '@angular/core';
import { PaypalServiceService } from 'src/app/services/paypal/paypal-service.service';

declare var $: any;
declare var paypal: any;

@Component({
  selector: 'app-paypall',
  templateUrl: './paypall.component.html',
  styleUrls: ['./paypall.component.css'],
})
export class PaypallComponent implements OnInit {
  @ViewChild('paypal', { static: true })
  paypalElement!: ElementRef;

  paidFor = false;

  customerInfor: any;

  constructor(private payPallService: PaypalServiceService) {}

  ngOnInit(): void {
    this.customerInfor = JSON.parse(sessionStorage.getItem('TicketInfor')!);

    let description =
      'Book ticket' +
      this.customerInfor.ticketOneWay.departure.ben_toi +
      ' ⇒ ' +
      this.customerInfor.ticketOneWay.destination.ben_toi;
    let price = this.customerInfor.ticketOneWay.totalMoney / 22000;
    this.payMent(description,price);
  }

  payMent(description:any, price:any){
    paypal.Buttons({
      createOrder: (data:any, actions:any) => {
        return actions.order.create({
          purchase_units: [
            {
              description: description,
              amount: {
                currency_code: 'USD',
                value: price.toFixed(2)
              }
            }
          ]
        });
      },
      onApprove: async (data:any, actions:any) => {
        const order = await actions.order.capture();
      /*   this.payPallService.getTransectionPayPal() */
      this.payPallService.getTokenPayPal().subscribe(
        data => {
          this.payPallService.getTransectionPayPal(data.orderID,data.accessToken).subscribe(
            data => {
              let ticket ={
               /*  gio_chay:this.customerInfor.ticketOneWay.runTime,
                gio_ket_thuc:"",
                id_tuyen_xe: this.customerInfor.routerId,
                sdt: this.customerInfor.phone,
                email: this.customerInfor.email,
                date: this.customerInfor.ticketOneWay.dayGo,
                gia_ve: this.customerInfor.ticketOneWay.totalMoney,
                paypal_id: paypalId,
                diem_xuong: this.customerInfor.ticketOneWay.destination,
                slot: slots, */
              }
            }
          )
        }
      )
      }
    })
  }

  onClose() {
    $('#myModalPay').hide();
  }
}
